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
@Document(indexName = "item" , shards = 1)
public class Item implements Serializable {
    @Id
    Long id;

    //标题
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    String title;

    @Field(type = FieldType.Keyword)
    String category;

    @Field(type = FieldType.Keyword)
    String brand; // 品牌

    @Field(type = FieldType.Double)
    Double price; // 价格

    @Field(type = FieldType.Keyword,index = false)
    String images; // 图片地址
}
