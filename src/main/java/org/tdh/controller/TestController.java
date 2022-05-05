package org.tdh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tdh.service.CkCkdxService;

/**
 * @author Puti
 * @date 2022/5/5 16:05
 */
@Controller
public class TestController {

    @Autowired
    private CkCkdxService ckCkdxService;

    @RequestMapping("/a")
    @ResponseBody
    public String hello() {
        return ckCkdxService.getAll().toString();
    }
}
