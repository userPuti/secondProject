package org.tdh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tdh.cache.Caches;
import org.tdh.domain.CkCkxz;
import org.tdh.domain.CkXzdw;
import org.tdh.domain.TsBzdm;
import org.tdh.domain.TsDm;
import org.tdh.dto.CkxzDto;
import org.tdh.service.CkCkxzService;
import org.tdh.util.response.ResResult;
import org.tdh.util.response.ResponseVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Puti
 * @date 2022/5/6 15:41
 */
@Controller
@RequestMapping("/webapp/wdcx")
public class MainPageController {
    private Logger log = LoggerFactory.getLogger(MainPageController.class);

    @Autowired
    private CkCkxzService ckxzService;

    /**
     * 加载查控协执信息
     *
     * @return 有信息返回xml的String，否则返回空字符串
     */
    @RequestMapping(value = "/listwdcx.do", produces = "application/xml;charset=utf-8")
    @ResponseBody
    public String listWdcx(CkxzDto ckxzDto, HttpServletRequest request) {
        log.debug("加载查控协执表格");
        String ckxzXml = ckxzService.CkckxzInfo(ckxzDto);
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

        for (TsDm ckzt : Caches.CKZT_MAP.get("ckzts")) {
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
     * @param bdhms
     * @return
     */
    @RequestMapping(value = "/batchDel.do", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResponseVO batchDel(@RequestParam("bdhms") String bdhms) {
        String[] idArray = bdhms.trim().split(",");
        int total = idArray.length;
        int succCount = ckxzService.batchDel(bdhms);
        int fail = total - succCount;
        if (succCount > 0) {
            return ResResult.successWithData("成功删除了" + succCount + "条数据,失败了" + fail + "条");
        } else {
            return ResResult.fail();
        }
    }


    /**
     * 用于跳转到添加页面
     *
     * @param func 功能参数，为添加add
     * @return 返回一个ModelAndView对象，同时传递func参数到userForm页面
     */
    @RequestMapping("/cxsfdj.do")
    public ModelAndView cxsfdj(String func) {
        ModelAndView modelAndView = new ModelAndView("wdcx_cxsqdj");

        StringBuilder zjflString = new StringBuilder();
        for (TsDm zjfl : Caches.ZJFL_MAP.get("zjfl")) {
            zjflString.append("<option value=\"").append(zjfl.getCode()).append("\">").append(zjfl.getMc()).append("</option>");
        }

        if (!"".equals(zjflString)) {
            log.debug("证件类型下拉框加载成功：{}", zjflString);
            modelAndView.addObject("zjflOption", zjflString.toString());
        } else {
            log.info("证件类型下拉框加载为空,可能出现问题");
        }

        StringBuilder sasfString = new StringBuilder();
        for (TsBzdm sasf : Caches.SASF_MAP.get("sasf")) {
            sasfString.append("<option value=\"").append(sasf.getCode()).append("\">").append(sasf.getMc()).append("</option>");
        }
        if (!"".equals(sasfString)) {
            log.debug("涉案身份下拉框加载成功：{}", sasfString);
            modelAndView.addObject("sasfOption", sasfString.toString());
        } else {
            log.info("涉案身份下拉框加载为空,可能出现问题");
        }

        StringBuilder gjString = new StringBuilder();
        for (TsBzdm gj : Caches.GJ_MAP.get("gj")) {
            gjString.append("<option value=\"").append(gj.getCode()).append("\">").append(gj.getMc()).append("</option>");
        }
        if (!"".equals(gjString)) {
            log.debug("国籍下拉框加载成功：{}", gjString);
            modelAndView.addObject("gjOption", gjString.toString());
        } else {
            log.info("国籍下拉框加载为空,可能出现问题");
        }

        List<TsDm> cklbs = Caches.CKLB_MAP.get("cklb");
        StringBuilder ckxzString = new StringBuilder();
        for (TsDm cklb : cklbs) {
            ckxzString.append(" <tr ><td class=\"tdTitle\" colspan = \"2\" >")
                    .append("   <label ><input class=\"inputCheck\" type = \"checkbox\" / >").append(cklb.getMc())
                    .append("</label ></td ><td colspan = \"5\" class=\"tdCont_pd\" >");

            int i = 0;
            for (CkXzdw xzdw : Caches.CKXZDWFL_MAP.get(cklb.getCode())) {
                ckxzString.append("<label ><input class=\"inputCheck\" type = \"checkbox\" / >").append(xzdw.getMc()).append("</label >");
                i++;
                if (0 == (i % 3)) {
                    ckxzString.append("<br/>");
                }
            }

            ckxzString.append("</td></tr>");
        }

        modelAndView.addObject("xzdw", ckxzString);

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
        modelAndView.addObject("ckxz",ckxz);
        return modelAndView;
    }
}
