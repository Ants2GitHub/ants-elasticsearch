package com.ants.service;

import com.ants.response.JsonResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
  * @description 操作ES 6.x 的service
  * @author ants·ht
  * @date 2018/6/4 14:37
*/
public interface DemoService {

    JsonResponse testDemo(HttpServletRequest request, HttpServletResponse response);
}
