package com.ypp.tunte.sample.es.one.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

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
@Document(indexName = "product" , shards = 1)
public class Product implements Serializable {
    @Id
    private Long id;

    private String produceNo;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String name;

    private double price;

    @Field(type = FieldType.Date)
    private Date date;
}
