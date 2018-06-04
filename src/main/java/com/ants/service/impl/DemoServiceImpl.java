package com.ants.service.impl;

import com.ants.response.JsonResponse;
import com.ants.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
  * @description 操作 ES 6.x 的 demo  service
  * @author ants·ht
  * @date 2018/6/4 14:39
*/
@Service
@Slf4j
public class DemoServiceImpl implements DemoService {

    private static final String DEMO_INDEX_NAME = "es_demo_index";

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Override
    public JsonResponse testDemo(HttpServletRequest request, HttpServletResponse response) {

        try {
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(new CreateIndexRequest(DEMO_INDEX_NAME));
            return new JsonResponse(HttpStatus.OK.value(),HttpStatus.OK.name(),createIndexResponse.toString());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("create demo index error.....,e={}",e);
            return new JsonResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(),null);
        }
    }
}
