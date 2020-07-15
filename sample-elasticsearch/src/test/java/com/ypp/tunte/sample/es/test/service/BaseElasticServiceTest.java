package com.ypp.tunte.sample.es.test.service;



import com.ypp.tunte.sample.es.test.SampleEsApplicationTest;
import org.junit.Test;

import javax.annotation.Resource;


/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/16
 **/
public class BaseElasticServiceTest extends SampleEsApplicationTest {

    @Resource
    private BaseElasticService baseElasticService;

    @Test
    void createIndex() {
        String mapping = " \"mappings\" : { \"properties\" : {  \"age\" : { \"type\" : \"long\" },\"name\" : {\"type\" : \"text\",\"fields\" : {\"keyword\" : {  \"type\" : \"keyword\",\"ignore_above\" : 256}}} }}";
        baseElasticService.createIndex("yanpingping",mapping);
    }

    @Test
    void indexExist() {
    }

    @Test
    void isExistsIndex() {
    }

    @Test
    void buildSetting() {
    }

    @Test
    void insertOrUpdateOne() {
    }

    @Test
    void insertBatch() {
    }

    @Test
    void deleteBatch() {
    }

    @Test
    void search() {
    }

    @Test
    void deleteIndex() {
    }

    @Test
    void deleteByQuery() {
    }
}