package com.ypp.tunte.sample.es.test.org;

import com.ypp.tunte.sample.es.test.SampleEsApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 与Get API一样，它使用GetRequest。 支持其所有可选参数。 由于exist（）仅返回true或false，因此我们建议您关闭_source和任何存储字段的提取功能，以使请求稍微减轻一些：
 *
 * @author yanpp
 * @createDateTime 2020/6/2
 **/
@Slf4j
public class ExistsTest extends SampleEsApplicationTest {
    @Resource
    RestHighLevelClient client;

    @Test
    public void firstTest() throws IOException, InterruptedException {

        GetRequest getRequest = new GetRequest(
                "posts", //索引
                "2");   //文档ID
        //禁用获取_source。
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        //禁用获取存储的字段
        getRequest.storedFields("_none_");


        /****同步执行***/
        //boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
        //log.info("是否存在:{}",exists);

        /****异步执行***/
        client.existsAsync(getRequest,RequestOptions.DEFAULT,listener);
        Thread.sleep(100);

    }

    ActionListener<Boolean> listener = new ActionListener<Boolean>() {
        @Override
        public void onResponse(Boolean exists) {
                log.info("是否存在:{}",exists);
        }

        @Override
        public void onFailure(Exception e) {
            log.error("请求异常:{}",e.getMessage());
        }
    };



}
