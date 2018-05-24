package com.ants.service;

import com.ants.documents.Customer;
import com.ants.repository.CustomerRepository;
import com.ants.response.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
  * @description Customer 的业务层
  * @author ants·ht
  * @date 2018/5/24 13:42
*/
@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    public JsonResponse saveInfo() {
        customerRepository.save(new Customer(1,"张三","北京"));
        customerRepository.save(new Customer(2,"李四","天津"));
        return new JsonResponse(HttpStatus.OK.value(),"SUC",null);
    }


    public JsonResponse findAll() {
        List<Customer> res = new ArrayList<>();
        Iterator<Customer> iterator = customerRepository.findAll().iterator();
        while (iterator.hasNext()) {
            res.add(iterator.next());
        }

        return new JsonResponse(HttpStatus.OK.value(),"suc",res);
    }

    public JsonResponse findByName(String name) {
        List<Customer> byName = customerRepository.findByName(name);
        return new JsonResponse(HttpStatus.OK.value(),"",byName);
    }

}
