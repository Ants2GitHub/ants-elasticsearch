package com.ants.repository;

import com.ants.documents.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
  * @description
  * @author ants·ht
  * @date 2018/5/24 13:41
*/
public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {


    public List<Customer> findByName(String name);
}
