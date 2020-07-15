package com.ypp.tunte.sample.zk;

import com.google.common.collect.Maps;
import com.ypp.tunte.es.entity.ElasticEntity;
import com.ypp.tunte.es.service.BaseElasticService;
import com.ypp.tunte.sample.jasypt.zk.ZKSampleApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/8
 **/
@RunWith(SpringRunner.class)
@SpringBootTest( classes = ZKSampleApplication.class)
public class EsTest {

    @Autowired
    private BaseElasticService baseElasticService;

    @Test
    public void insertOrUpdateOneTest(){
        ElasticEntity entity=new ElasticEntity();
        entity.setId("12");
        Map map=Maps.newHashMap();
        map.put("name","张大全");
        entity.setData(map);
        baseElasticService.insertOrUpdateOne("actors",entity);
    }

}
