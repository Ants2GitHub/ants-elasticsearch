package com.ants.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
  * @description 客户的文档
  * @author ants·ht
  * @date 2018/5/24 13:38
*/
@Data
@AllArgsConstructor
@Document(indexName = "es-customer",type = "customer",shards = 5,replicas = 1)
public class Customer {

    @Id
    private Integer id;

    private String name;

    private String address;
}
