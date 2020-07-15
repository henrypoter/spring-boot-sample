package com.ypp.tunte.sample.sb.es.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/17
 **/
@Document(indexName = "product_item", shards = 1, replicas = 0)//标记为文档类型，ndexName：对应索引库名称type：对应在索引库中的类型，shards：分片数量，默认5，replicas：副本数量，默认1
public class Item {


    @Id //主键
    private Long id;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    //标记为成员变量　　FieldType，可以是text、long、short、date、integer等　　text：存储数据时候，会自动分词，并生成索引　　keyword：存储数据时候，不会分词建立索引　　analyzer:分词器名称
    private String title; //标题
    @Field(type = FieldType.Keyword)
    private String category;// 分类
    @Field(type = FieldType.Keyword)
    private String brand; // 品牌
    @Field(type = FieldType.Double)
    private Double price; // 价格
    @Field(index = false, type = FieldType.Keyword)//index:是否索引
    private String images; // 图片地址

    public Item(){}

    public Item(Long id, String title, String category, String brand, Double price, String images) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", images='" + images + '\'' +
                '}';
    }
}