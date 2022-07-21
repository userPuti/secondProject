package org.tdh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tdh.cache.TsDmCache;
import org.tdh.domain.TsDm;
import org.tdh.dto.HomePageDto;
import org.tdh.service.CxsqService;
import org.tdh.util.response.ResResult;
import org.tdh.util.response.ResponseVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Puti
 * @date 2022/5/6 15:41
 */
@Controller
@RequestMapping("/webapp/wdcx")
public class MainPageController {
    private Logger log = LoggerFactory.getLogger(MainPageController.class);

    @Autowired
    private CxsqService cxsqService;

    /**
     * 加载查控协执信息
     *
     * @return 有信息返回xml的String，否则返回空字符串
     */
    @RequestMapping(value = "/listwdcx.do", produces = "application/xml;charset=utf-8")
    @ResponseBody
    public String listWdcx(HomePageDto homePageDto, HttpServletRequest request) {
        log.debug("加载查控协执表格");
        String ckxzXml = cxsqService.showCksqInfo(homePageDto);
        if (ckxzXml != null) {
            log.debug("表格加载完毕,{}", ckxzXml);
            return ckxzXml;
        }
        log.info("加载表格出现了问题");
        return "";
    }


    /**
     * 加载主页信息
     *
     * @return 带有主页视图和下拉框信息的ModelAndView
     */
    @RequestMapping("wdcx_main.do")
    public ModelAndView wdcx_main() {
        log.debug("加载主页面");
        ModelAndView mav = new ModelAndView("wdcx_main");
        StringBuilder sb = new StringBuilder();

        for (TsDm ckzt : TsDmCache.KIND_TSDM_MAP.get("CKZT")) {
            sb.append("<option value=\"").append(ckzt.getCode()).append("\">").append(ckzt.getMc()).append("</option>");
        }

        if (!"".equals(sb)) {
            log.debug("状态下拉框加载成功，{}", sb);
            mav.addObject("ztOption", sb.toString());
        } else {
            log.info("状态下拉框加载为空可能出现问题");
        }

        log.debug("主页面加载成功");
        return mav;
    }


    /**
     * 批量删除
     *
     * @param bdhms 表单号码
     * @return 成功返回ResResult.successWithData，
     *         其中包含的成功信息是删除成功的条数和失败的条数，
     *         失败了返回ResResult.fail()
     */
    @RequestMapping(value = "/batchDel.do", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseVO batchDel(@RequestParam("bdhms") String bdhms) {
        log.warn("正在批量删除表单号码为 {} 的信息", bdhms);
        String[] idArray = bdhms.trim().split(",");
        int total = idArray.length;
        int succCount = cxsqService.batchDel(idArray);
        int fail = total - succCount;
        if (succCount > 0) {
            log.info("删除成功，并向后端返回成功信息！");
            return ResResult.successWithData("成功删除了" + succCount + "条数据,失败了" + fail + "条");
        } else {
            log.info("删除失败，并向前端返回失败信息！");
            return ResResult.fail();
        }
    }
}