package org.tdh.controller;

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
import org.tdh.domain.CkCkxz;
import org.tdh.domain.CkXzdw;
import org.tdh.domain.TsDm;
import org.tdh.dto.CkxzDto;
import org.tdh.service.CkxzService;
import org.tdh.util.response.ResResult;
import org.tdh.util.response.ResponseVO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    private CkxzService ckxzService;


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
        log.debug("跳转到查询登记信息页面");
        return modelAndView;
    }


    /**
     * 根据bdhm来查询协执的信息，并传递给前端相应的数据
     *
     * @param bdhm 表单号码
     * @param func 只能是view和edit
     * @return ModelAndView
     */
    @RequestMapping("/viewXzInfo.do")
    public ModelAndView viewXzInfo(String bdhm, String func) {
        ModelAndView modelAndView = new ModelAndView("wdcx_cxsqdj");
        modelAndView.addObject("func", func);
        CkCkxz ckxz = ckxzService.viewCkxzInfo(bdhm);
        modelAndView.addObject("ckxz", ckxz);
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
    public ModelAndView getCxdxTab() {
        ModelAndView modelAndView = new ModelAndView("commonTable");
        loadSel(modelAndView);
        List<CkCkdx> ckCkdxList = new ArrayList<>();

        CkCkdx ckdx = new CkCkdx();
        String cklsh = UUID.randomUUID().toString().replaceAll("-", "");
        ckdx.setCklsh(cklsh);
        ckCkdxList.add(ckdx);

        modelAndView.addObject("ckCkdxList", ckCkdxList);
        return modelAndView;
    }

    /**
     * @param ckxzDto
     * @return
     */
    @RequestMapping("saveCkxz.do")
    @ResponseBody
    public ResponseVO saveCkxz(CkxzDto ckxzDto) {
        if (ckxzDto != null && !"".equals(ckxzDto)) {
            String xzdwmc = ckxzDto.getXzdwdm();
            //去除多余的一个逗号
            xzdwmc = xzdwmc.substring(0, xzdwmc.length() - 1);
            ckxzDto.setXzdwdm(xzdwmc);
            boolean isSuccess = ckxzService.insertCkxz(ckxzDto);
            if (isSuccess) {
                return ResResult.success();
            } else {
                return ResResult.fail();
            }
        }
        return ResResult.fail();
    }


    /**
     *
     * @param desc
     * @param files
     * @return
     * @throws IOException
     */
    @RequestMapping("upload.do")
    @ResponseBody
    public String testMutiUpload(@RequestParam("desc") String desc, @RequestParam("file") MultipartFile[] files) throws IOException {
        System.out.println("文件描述：" + desc);
        for (MultipartFile file : files) {
            InputStream inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            OutputStream outputStream = new FileOutputStream("C:\\tmp\\" + fileName);
            byte[] bs = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(bs)) != -1) {
                outputStream.write(bs, 0, len);
            }
            inputStream.close();
            outputStream.close();
        }
        return "success";
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
}
