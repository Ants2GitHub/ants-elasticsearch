package com.ants.controller;

import com.ants.response.JsonResponse;
import com.ants.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
  * @description 操作 6.x 的controller的demo
  * @author ants·ht
  * @date 2018/6/4 14:36
*/
@RestController
@RequestMapping("/elasticsearch/demo")
public class DemoController {

    @Autowired
    DemoService demoService;


    @RequestMapping("/create")
    public JsonResponse testDemo(HttpServletRequest request, HttpServletResponse response) {
        return demoService.testDemo(request,response);
    }
}
