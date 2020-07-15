package com.ypp.tunte.sample.sb.es.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/12
 **/
@Data
@Document(indexName = "kyhk_art",shards = 1,replicas = 1)
public class KyhkArt implements Serializable {
    @Field(type = FieldType.Text)
    private String title;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String context;
    @Field(type = FieldType.Integer)
    private int click;

    public KyhkArt() {
    }

    public KyhkArt(String title, String context, int click) {
        this.title = title;
        this.context = context;
        this.click = click;
    }
}
