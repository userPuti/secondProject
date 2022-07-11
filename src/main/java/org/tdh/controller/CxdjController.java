package org.tdh.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.tdh.cache.TsBzdmCache;
import org.tdh.cache.TsDmCache;
import org.tdh.domain.CkCkdx;
import org.tdh.domain.CkJz;
import org.tdh.domain.CkXzdw;
import org.tdh.domain.TsDm;
import org.tdh.dto.CxsqDto;
import org.tdh.service.CxsqService;
import org.tdh.util.response.ResResult;
import org.tdh.util.response.ResponseVO;
import org.tdh.vo.CkjzVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLDecoder;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Puti
 * @date 2022/5/14 9:48
 */
@Controller
@RequestMapping("/webapp/wdcx")
public class CxdjController {
    private Logger log = LoggerFactory.getLogger(CxdjController.class);

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
        log.debug("为跳往查询登记信息页面做准备！");
        ModelAndView modelAndView = new ModelAndView("wdcx_cxsqdj");
        loadChkBox(modelAndView);
        modelAndView.addObject("func", "add");
        String djpc = getUUID();
        modelAndView.addObject("djpc", djpc);
        log.debug("跳转到查询登记信息页面");
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
            e.printStackTrace();
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
     * @return
     */
    @RequestMapping("getCxdxTab.do")
    public ModelAndView getCxdxTab(String djpc) {
        ModelAndView modelAndView = new ModelAndView("commonTable");
        loadSel(modelAndView);
        List<CkCkdx> ckCkdxList = null;

        if (djpc == null || "".equals(djpc)) {
            ckCkdxList = new ArrayList<>();

            CkCkdx ckdx = new CkCkdx();
            String cklsh = getUUID();
            ckdx.setCklsh(cklsh);
            ckCkdxList.add(ckdx);
        } else {
            ckCkdxList = cxsqService.viewCkdxInfo(djpc);
        }

        modelAndView.addObject("ckCkdxList", ckCkdxList);
        return modelAndView;
    }

    /**
     * @param cxsqDto
     * @return
     */
    @RequestMapping("updateCxsqdj.do")
    @ResponseBody
    public ResponseVO updateCxsqdj(CxsqDto cxsqDto, HttpServletRequest request) {
        if (cxsqDto != null && !"".equals(cxsqDto)) {
            String xzdwmc = cxsqDto.getXzdwdm();
            //去除多余的一个逗号
            xzdwmc = xzdwmc.substring(0, xzdwmc.length() - 1);
            cxsqDto.setXzdwdm(xzdwmc);


            Optional fileOption = Optional.ofNullable(cxsqDto.getFiles());
            if (fileOption.isPresent()) {
                List<CkJz> files = cxsqDto.getFiles();

                for (int i = files.size() - 1; i >= 0; i--) {
                    Optional<Integer> xhOption = Optional.ofNullable(files.get(i).getXh());
                    //有序号，表明在数据库中已经存在该文件信息了，将它从List中删除
                    if (xhOption.isPresent()) {
                        files.remove(i);
                    }
                }
                cxsqDto.setFiles(files);
            }

            copyFileToFinalPath(cxsqDto, request);

            if (cxsqService.updateSqInfo(cxsqDto)) {
                return ResResult.success();
            } else {
                return ResResult.fail();
            }
        }
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
        if (cxsqDto != null && !"".equals(cxsqDto)) {
            String xzdwmc = cxsqDto.getXzdwdm();
            //去除多余的一个逗号
            xzdwmc = xzdwmc.substring(0, xzdwmc.length() - 1);
            cxsqDto.setXzdwdm(xzdwmc);

            copyFileToFinalPath(cxsqDto, request);

            boolean isSuccess = cxsqService.insertCksq(cxsqDto);

            if (isSuccess) {
                return ResResult.success();
            } else {
                return ResResult.fail();
            }
        }
        return ResResult.fail();
    }


    /**
     * 文件批量上传,创建一个临时文件，用于上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("upload.do")
    @ResponseBody
    public ResponseVO uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String fileId = getUUID();

        String fileName = file.getOriginalFilename();
        String wjlx = fileName.substring(fileName.lastIndexOf(".") + 1);
        String wjmc = fileName.substring(0, fileName.lastIndexOf('.'));

        String tempFilePath = request.getSession().getServletContext().getRealPath("tempFile");
        File fileDir = new File(tempFilePath);

        if (!fileDir.exists()) {
            fileDir.mkdir();
        }

        String date = getFormattedDate("yyyyMMdd");

        //根据日期生成一个文件夹保存临时文件
        String finalPath = tempFilePath + File.separator + date + File.separator + fileId + "." + wjlx;

        try {
            file.transferTo(new File(finalPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileInfo = "";

        try {
            List<CkjzVO> ckJzVOs = new ArrayList<>();

            CkjzVO tempJz = new CkjzVO();
            tempJz.setWjlx(wjlx);
            tempJz.setWjmc(wjmc);
            tempJz.setPath(finalPath);
            tempJz.setTmepUuid(fileId);
            ckJzVOs.add(tempJz);

            fileInfo = new ObjectMapper().writeValueAsString(ckJzVOs);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

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
        log.info("正在下载文件...");
        try {
            path = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("文件地址解码错误！", e);
        }
        //获取服务器文件夹的地址
        String fileDestPath = session.getServletContext().getRealPath("fileDestPath");
        //定位到要下载的文件
//        //todo 注意这里的finalpath不对，这里的finalpath需要加上一个日期的文件夹
//        String finalPath = fileDestPath + File.separator + fileName;
        String wjlx = path.substring(path.lastIndexOf("."));

        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + getUUID() + wjlx);
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                response.getOutputStream().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 加载下拉框
     *
     * @param modelAndView
     */
    private void loadSel(ModelAndView modelAndView) {
        modelAndView.addObject("zjfl", TsDmCache.KIND_TSDM_MAP.get("ZJFL"));
        modelAndView.addObject("sasf", TsBzdmCache.KIND_TSBZDM_MAP.get("05036"));
        modelAndView.addObject("gj", TsBzdmCache.KIND_TSBZDM_MAP.get("00004"));
    }


    /**
     * 加载复选框
     *
     * @param modelAndView
     */
    private void loadChkBox(ModelAndView modelAndView) {
        log.debug("正在加载复选框的信息！");
        Map<String, List<CkXzdw>> ckxzdwMap = new HashMap<>();
        List<CkXzdw> ckxzdwList = new ArrayList<>();

        if (CkxzdwCache.XZDWFL_XZDW_MAP != null) {
            List<TsDm> tsDmList = TsDmCache.KIND_TSDM_MAP.get("CKLB");

            for (TsDm tsDm : tsDmList) {
                ckxzdwMap.put(tsDm.getBz(), CkxzdwCache.XZDWFL_XZDW_MAP.get(tsDm.getCode()));
            }

            log.debug("复选框的信息：{}", ckxzdwMap);
            modelAndView.addObject("ckxzdwMap", ckxzdwMap);
        } else {
            log.info("缓存里面的东西为空，无法传递协执单位信息！");
        }
    }


    /**
     * 获取一个32位的uuid
     *
     * @return 一个随机的32位uuid
     */
    private String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    /**
     * @param cxsqDto
     * @param request
     */
    private void copyFileToFinalPath(CxsqDto cxsqDto, HttpServletRequest request) {
        Optional files = Optional.ofNullable(cxsqDto.getFiles());
        if (files.isPresent()) {
            String fileDestPath = request.getSession().getServletContext().getRealPath("fileDestPath");
            fileDestPath = fileDestPath + File.separator + getFormattedDate("yyyyMMdd");
            File fileDir = new File(fileDestPath);

            if (!fileDir.exists()) {
                fileDir.mkdir();
            }

            int xh = cxsqService.getMaxXh(cxsqDto.getDjpc()) + 1;

            //将文件从临时文件复制到最终文件
            for (CkJz file : cxsqDto.getFiles()) {
                String filePath = fileDestPath + File.separator + getUUID() + "." + file.getWjlx();

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
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取一个日期字符串
     *
     * @param pattern 格式化
     * @return 时间字符串
     */
    private String getFormattedDate(String pattern) {
        SimpleDateFormat smf = new SimpleDateFormat(pattern);
        return smf.format(new Date());
    }
}
