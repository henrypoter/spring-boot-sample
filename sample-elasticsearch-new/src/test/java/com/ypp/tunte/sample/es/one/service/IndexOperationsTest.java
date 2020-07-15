package com.ypp.tunte.sample.es.one.service;

import com.ypp.tunte.sample.es.one.SpringEsDataSample;
import com.ypp.tunte.sample.es.one.pojo.Item;
import com.ypp.tunte.sample.es.one.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/5
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringEsDataSample.class)
@Slf4j
public class IndexOperationsTest {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;


    @Test
    public void searchTest() {

        Item item = elasticsearchRestTemplate.queryForObject(GetQuery.getById("1"),Item.class);
        log.info("{}",item);

    }
}
