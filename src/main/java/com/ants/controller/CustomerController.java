package com.ants.controller;

import com.ants.response.JsonResponse;
import com.ants.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
  * @description 客户的出口API
  * @author ants·ht
  * @date 2018/5/24 13:37
*/
@RestController
@RequestMapping(value = "/es/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    /**
     * @description 数据保存
     * @author ants·ht
     * @date 2018/5/24 13:59
     * @param
     * @return
    */
    @RequestMapping(value = "save",method = RequestMethod.GET)
    public JsonResponse saveDocument(HttpServletRequest request, HttpServletResponse response) {
        return customerService.saveInfo();
    }

    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public JsonResponse findAll(HttpServletRequest request,HttpServletResponse response) {
        return customerService.findAll();
    }

    @RequestMapping(value = "/query/{name}",method = RequestMethod.GET)
    public JsonResponse findByName(HttpServletRequest request,HttpServletResponse response,@PathVariable(value = "name")  String name) {
        return customerService.findByName(name);
    }
}
