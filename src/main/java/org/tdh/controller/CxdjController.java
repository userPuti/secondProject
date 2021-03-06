package org.tdh.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.tdh.cache.CkxzdwCache;
import org.tdh.domain.*;
import org.tdh.dto.CxsqDto;
import org.tdh.service.CxsqService;
import org.tdh.util.TranslateUtils;
import org.tdh.util.Utils;
import org.tdh.util.response.ResResult;
import org.tdh.util.response.ResponseVO;
import org.tdh.vo.CkjzVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * @author Puti
 * @date 2022/5/14 9:48
 */
@Controller
@RequestMapping("/webapp/wdcx")
public class CxdjController {
    private Logger log = LoggerFactory.getLogger(CxdjController.class);

    //查控申请对应的service
    @Autowired
    private CxsqService cxsqService;


    /**
     * 用于跳转到添加页面
     *
     * @param func 功能参数，为添加add
     * @return 返回一个ModelAndView对象，同时传递func参数到userForm页面
     */
    @RequestMapping("/cxsfdj.do")
    public ModelAndView cxsfdj(String func) {
        log.info("为跳往查询登记信息页面做准备！");
        ModelAndView modelAndView = new ModelAndView("wdcx_cxsqdj");
        loadChkBox(modelAndView);
        modelAndView.addObject("func", "add");
        String djpc = Utils.getUUID();
        modelAndView.addObject("djpc", djpc);
        log.info("跳转到查询登记信息页面");
        return modelAndView;
    }


    /**
     * 根据bdhm来查询协执的信息，并传递给前端相应的数据
     *
     * @param djpc 登记批次
     * @param func 只能是view和edit
     * @return ModelAndView
     */
    @RequestMapping("/viewSqInfo.do")
    public ModelAndView viewSqInfo(String djpc, String func) {
        log.info("查看查询登记表!");
        log.debug("当前查询登记表的批次为：{}", djpc);
        ModelAndView modelAndView = new ModelAndView("wdcx_cxsqdj");
        modelAndView.addObject("func", func);
        modelAndView.addObject("djpc", djpc);

        List<String> xzdwdms = cxsqService.getXzdwdm(djpc);
        String xzsm = cxsqService.getXzsm(djpc);

        try {
            List<CkJz> ckjzs = cxsqService.getCkJz(djpc);
            String ckjzsJson = new ObjectMapper().writeValueAsString(ckjzs).replaceAll("\"", "&quot;");
            modelAndView.addObject("ckjzs", ckjzsJson);
        } catch (JsonProcessingException e) {
            log.error("查看时json解析出现错误：{}", e);
        }

        modelAndView.addObject("xzdwdms", xzdwdms);
        modelAndView.addObject("xzsm", xzsm);

        loadSel(modelAndView);
        loadChkBox(modelAndView);
        return modelAndView;
    }


    /**
     * 查控对象信息框
     *
     * @return ModelAndView
     */
    @RequestMapping("getCxdxTab.do")
    public ModelAndView getCxdxTab(String djpc) {
        log.debug("加载查控对象的表格...,当前批次为：{}", djpc);

        ModelAndView modelAndView = new ModelAndView("commonTable");
        loadSel(modelAndView);
        List<CkCkdx> ckCkdxList = null;

        if (djpc == null || "".equals(djpc)) {
            ckCkdxList = new ArrayList<>();

            CkCkdx ckdx = new CkCkdx();
            String cklsh = Utils.getUUID();
            ckdx.setCklsh(cklsh);
            ckCkdxList.add(ckdx);

            modelAndView.addObject("count", 1);
        } else {
            ckCkdxList = cxsqService.viewCkdxInfo(djpc);
            modelAndView.addObject("count", ckCkdxList.size());
        }

        modelAndView.addObject("ckCkdxList", ckCkdxList);

        log.debug("{} 批次的查控对象信息为：{}", djpc, ckCkdxList);
        return modelAndView;
    }


    /**
     * 更新查控申请表
     *
     * @param cxsqDto   查控申请数据传输对象
     * @param delFileXh 被删除文件的序号
     * @param request
     * @return 成功返回ResResult.success()，否则返回ResResult.fail()
     */
    @RequestMapping("updateCxsqdj.do")
    @ResponseBody
    public ResponseVO updateCxsqdj(CxsqDto cxsqDto, String delFileXh, HttpServletRequest request) {
        log.info("更新的信息中...");
        if (cxsqDto != null && !"".equals(cxsqDto)) {
            log.debug("更新批次为 {} 的信息", cxsqDto.getDjpc());

            String xzdwmc = cxsqDto.getXzdwdm();
            //去除多余的一个逗号
            xzdwmc = xzdwmc.substring(0, xzdwmc.length() - 1);
            cxsqDto.setXzdwdm(xzdwmc);

            List<CkJz> updateFiles = new ArrayList<>();

            //进行一下简单的判断，根据传递过来的序号信息来进行判断是否在数据库中
            List<CkJz> tempFiles = cxsqDto.getFiles();
            if (null != tempFiles) {
                for (int i = tempFiles.size() - 1; i >= 0; i--) {
                    if (-1 != tempFiles.get(i).getXh()) {
                        tempFiles.remove(i);
                    }
                }
                cxsqDto.setFiles(tempFiles);
            }

            //不在数据库中存在的文件，需要将他移动到最终的文件位置
            copyFileToFinalPath(cxsqDto, request);

            //将简单处理过的需要的数据带到service中去进行处理
            if (cxsqService.updateSqInfo(cxsqDto, delFileXh)) {
                log.info("更新成功！");
                return ResResult.success();
            } else {
                log.info("更新失败！");
                return ResResult.fail();
            }
        }
        log.info("更新对象为空，无法更新！");
        return ResResult.fail();
    }


    /**
     * 保存查控申请
     *
     * @param cxsqDto 查控申请入参对象
     * @return 成功返回ResponseVO.success, 否则返回ResponseVO.fail
     */
    @RequestMapping("saveCksq.do")
    @ResponseBody
    public ResponseVO saveCksq(CxsqDto cxsqDto, HttpServletRequest request) {
        log.info("保存查控申请表信息中...");
        if (cxsqDto != null && !"".equals(cxsqDto)) {
            String xzdwmc = cxsqDto.getXzdwdm();
            //去除多余的一个逗号
            xzdwmc = xzdwmc.substring(0, xzdwmc.length() - 1);
            cxsqDto.setXzdwdm(xzdwmc);

            copyFileToFinalPath(cxsqDto, request);

            boolean isSuccess = cxsqService.insertCksq(cxsqDto);

            if (isSuccess) {
                log.info("查控申请表保存成功！");
                return ResResult.success();
            } else {
                log.info("查控申请表保存失败！");
                return ResResult.fail();
            }
        }
        log.info("没有需要保存的信息!");
        return ResResult.fail();
    }


    /**
     * 文件批量上传,创建一个临时文件，用于上传
     *
     * @param file
     * @return
     */
    @RequestMapping("upload.do")
    @ResponseBody
    public ResponseVO uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        log.info("将文件上传到临时文件！");
        String fileId = Utils.getUUID();

        String fileName = file.getOriginalFilename();
        String wjlx = fileName.substring(fileName.lastIndexOf(".") + 1);
        String wjmc = fileName.substring(0, fileName.lastIndexOf('.'));

        String tempFilePath = request.getSession().getServletContext().getRealPath("tempFile");
        File fileDir = new File(tempFilePath);

        if (!fileDir.exists()) {
            fileDir.mkdir();
        }

        String date = Utils.getFormattedDate("yyyyMMdd");

        //根据日期生成一个文件夹保存临时文件
        String finalPath = tempFilePath + File.separator + date + File.separator + fileId + "." + wjlx;

        try {
            file.transferTo(new File(finalPath));
        } catch (IOException e) {
            log.error("文件上传出现错误：{}", e);
            throw new RuntimeException("文件上传失败！");
        }

        String fileInfo = "";

        try {
            List<CkjzVO> ckJzVOs = new ArrayList<>();

            CkjzVO tempJz = new CkjzVO();
            tempJz.setWjlx(wjlx);
            tempJz.setWjmc(wjmc);
            tempJz.setPath(finalPath);
            tempJz.setTempUuid(fileId);
            ckJzVOs.add(tempJz);

            fileInfo = new ObjectMapper().writeValueAsString(ckJzVOs);
        } catch (JsonProcessingException e) {
            log.error("数据转换为json格式是出现错误 ：{}", e);
            throw new RuntimeException("数据转换为json格式是出现错误!");
        }

        log.debug("文件上传成功传回到后台的信息：{}", fileInfo);
        return ResResult.successWithData(fileInfo);
    }


    /**
     * 文件下载
     *
     * @param path     文件名
     * @param response
     */
    @RequestMapping("/downloadFile.do")
    public void downloadFile(String path, HttpSession session, HttpServletResponse response) {
        try {
            path = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("文件地址解码错误！", e);
        }
        //获取服务器文件夹的地址
        String wjlx = path.substring(path.lastIndexOf("."));

        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + Utils.getUUID() + wjlx);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
            //通过response获取ServletOutputStream对象(out)
            int b = 0;
            byte[] buffer = new byte[1024];
            while (b != -1) {
                b = fileInputStream.read(buffer);
                if (b != -1) {
                    response.getOutputStream().write(buffer, 0, b);//写到输出流(out)中
                }
            }
        } catch (IOException e) {
            log.error("文件下载时出现错误 ：{}", e);
            throw new RuntimeException("文件下载时出现错误");
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                response.getOutputStream().flush();
            } catch (IOException e) {
                log.info("文件流操作失败：e", e);
                throw new RuntimeException("文件流操作失败");
            }
        }
    }


    /**
     * 发送查询申请表
     * @param djpc 登记批次
     * @return 成功返回ResResult.success()，否则返回ResResult.fail()
     */
    @RequestMapping("/send.do")
    @ResponseBody
    public ResponseVO sendCxsq(String djpc) {
        if (null != djpc && !"".equals(djpc)) {
            if (cxsqService.sendCxsq(djpc)) {
                return ResResult.success();
            } else {
                return ResResult.fail();
            }
        }
        return ResResult.fail();
    }


    /**
     * 加载下拉框
     *
     * @param modelAndView
     */
    private void loadSel(ModelAndView modelAndView) {
        List<TsDm> zjfl = TranslateUtils.getTsDmByKind("zjfl");
        List<TsBzdm> sasf = TranslateUtils.getTsbzdmByKind("05036");
        List<TsBzdm> gj = TranslateUtils.getTsbzdmByKind("00004");

        log.debug("加载下拉框信息：{},{},{}", zjfl, sasf, gj);
        modelAndView.addObject("zjfl", zjfl);
        modelAndView.addObject("sasf", sasf);
        modelAndView.addObject("gj", gj);
    }


    /**
     * 加载复选框
     *
     * @param modelAndView
     */
    private void loadChkBox(ModelAndView modelAndView) {
        log.info("正在加载复选框的信息！");
        Map<String, List<CkXzdw>> ckxzdwMap = new HashMap<>();
        List<CkXzdw> ckxzdwList = new ArrayList<>();

        if (CkxzdwCache.XZDWFL_XZDW_MAP != null) {
            List<TsDm> tsDmList = TranslateUtils.getTsDmByKind("cklb");

            for (TsDm tsDm : tsDmList) {
                ckxzdwMap.put(tsDm.getBz(), TranslateUtils.getCkxzdwByCode(tsDm.getCode()));
            }

            log.debug("复选框的信息：{}", ckxzdwMap);
            modelAndView.addObject("ckxzdwMap", ckxzdwMap);
        } else {
            log.info("缓存里面的东西为空，无法传递协执单位信息！");
        }
    }


    /**
     * 将临时文件复制到最终的文件位置
     *
     * @param cxsqDto 查询申请的数据传输对象
     * @param request
     */
    private void copyFileToFinalPath(CxsqDto cxsqDto, HttpServletRequest request) {
        Optional files = Optional.ofNullable(cxsqDto.getFiles());
        if (files.isPresent()) {
            String fileDestPath = request.getSession().getServletContext().getRealPath("fileDestPath");
            fileDestPath = fileDestPath + File.separator + Utils.getFormattedDate("yyyyMMdd");
            File fileDir = new File(fileDestPath);

            if (!fileDir.exists()) {
                fileDir.mkdir();
            }

            int xh = cxsqService.getMaxXh(cxsqDto.getDjpc()) + 1;

            //将文件从临时文件复制到最终文件
            for (CkJz file : cxsqDto.getFiles()) {
                String filePath = fileDestPath + File.separator + Utils.getUUID() + "." + file.getWjlx();

                //将文件复制到最终地址
                copyFileUsingFileChannels(new File(file.getPath()), new File(filePath));

                //更新文件的最终地址
                file.setPath(filePath);
                file.setXh(xh++);
                file.setDjpc(cxsqDto.getDjpc());
                file.setLastupdate(new Date());
            }
        }

    }

    /**
     * 文件复制
     *
     * @param source 文件源地址
     * @param dest   文件目的地址
     */
    private void copyFileUsingFileChannels(File source, File dest) {
        if (null != source && null != dest) {
            try {
                log.debug("文件的源地址：{}, 文件的目的地址:{}", source, dest);
                FileUtils.copyFile(source, dest);
            } catch (IOException e) {
                log.error("文件复制出现错误:{}", e);
                throw new RuntimeException("文件复制出现错误!");
            }
        } else {
            return;
        }
    }
}
