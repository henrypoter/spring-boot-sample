package com.ypp.tunte.sample.es.one.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/5
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "keyu_user" , shards = 1)
public class User   implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private int age;

}
