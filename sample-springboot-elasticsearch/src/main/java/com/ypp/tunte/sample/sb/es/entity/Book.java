package com.ypp.tunte.sample.sb.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/16
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Document(indexName = "school")
public class Book {
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String name;
}
