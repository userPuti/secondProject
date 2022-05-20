package org.tdh.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tdh.cache.Caches;
import org.tdh.domain.CkCkdx;
import org.tdh.domain.CkCkxz;
import org.tdh.domain.CkXzdw;
import org.tdh.domain.TsDm;
import org.tdh.service.CkCkdxService;
import org.tdh.service.CkCkxzService;
import org.tdh.util.response.ResResult;
import org.tdh.util.response.ResponseVO;

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
    private CkCkxzService ckxzService;

    @Autowired
    private CkCkdxService ckdxService;


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
     * 添加一个查控对象
     *
     * @return
     */
    @RequestMapping("/addCxdx.do")
    @ResponseBody
    public ResponseVO addCxdx(CkCkdx ckdx) {
        if (ckdx != null) {
            log.debug("正在插入数据：{}", ckdx);

            UUID cklsh = UUID.randomUUID();
            ckdx.setCklsh(cklsh.toString().replaceAll("-", ""));
            Date now = new Date();
            ckdx.setLastupdate(now);
            ckdx.setZt("10");

            boolean isSucc = ckdxService.insertCkdx(ckdx);

            if (isSucc) {
                log.debug("插入数据成功！,返回给前端：{}", ResResult.success());
                return ResResult.success();
            } else {
                log.info("插入数据失败");
                return ResResult.fail();
            }
        }
        log.info("查控协执对象为空！");
        return ResResult.fail();
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
     * @param djpc
     * @return
     */
    @RequestMapping("getCxdxTab.do")
    public ModelAndView getCxdxTab(String djpc) {
        ModelAndView modelAndView = new ModelAndView("commonTable");
        loadSel(modelAndView);
//        List<CkCkdx> ckCkdxList = new ArrayList<>();
//        if("".equals(djpc)){
//            CkCkdx ckdx = new CkCkdx();
//            ckdx.setCklsh("312312312");
//            ckCkdxList.add(ckdx);
//        }else{
//            //todo
//        }
//        mav.addObject("ckCkdxList",ckCkdxList);
        return modelAndView;
    }


    /**
     * 本类中用于加载下拉框的公用函数
     *
     * @param modelAndView
     */
    private void loadSel(ModelAndView modelAndView) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            modelAndView.addObject("zjfl", objectMapper.writeValueAsString(Caches.ZJFL_MAP.get("zjfl")).replaceAll("\"", "&quot;"));
            modelAndView.addObject("sasf", objectMapper.writeValueAsString(Caches.SASF_MAP.get("sasf")).replaceAll("\"", "&quot;"));
            modelAndView.addObject("gj", objectMapper.writeValueAsString(Caches.GJ_MAP.get("gj")).replaceAll("\"", "&quot;"));
        } catch (JsonProcessingException e) {
            log.error("需要加载的下拉框的信息转化为json对象失败", e);
        }
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

        if (Caches.CKLB_MAP != null && Caches.CKXZDWFL_MAP != null) {
            for (TsDm cklb : Caches.CKLB_MAP.get("cklb")) {
                for (CkXzdw xzdw : Caches.CKXZDWFL_MAP.get(cklb.getCode())) {
                    ckxzdwList.add(xzdw);
                }
                List<CkXzdw> temp = new ArrayList<>(ckxzdwList);
                ckxzdwMap.put(cklb.getMc(), temp);
                ckxzdwList.clear();
            }
            log.debug("复选框的信息：{}", ckxzdwMap);
            try {
                modelAndView.addObject("ckxzdwMap", new ObjectMapper().writeValueAsString(ckxzdwMap).replaceAll("\"", "&quot;"));
            } catch (JsonProcessingException e) {
                log.error("查控协执单位信息转化为json对象失败：", e);
            }
        } else {
            log.info("缓存里面的东西为空，无法传递协执单位信息！");
        }
    }
}
