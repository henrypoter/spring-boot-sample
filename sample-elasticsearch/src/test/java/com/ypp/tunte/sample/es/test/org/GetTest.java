package com.ypp.tunte.sample.es.test.org;

import com.ypp.tunte.sample.es.test.SampleEsApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/1
 **/
@Slf4j
public class GetTest extends SampleEsApplicationTest {
    @Resource
    RestHighLevelClient client;

    @Test
    public void getFirstTest() throws IOException {

        GetRequest getRequest = new GetRequest("posts","2");

        //禁用源检索，默认启用
        getRequest.fetchSourceContext(FetchSourceContext.DO_NOT_FETCH_SOURCE);

        /***************指定获取数据源 start************/
        String[] includes = new String[]{"message","*Data"};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true,includes,excludes);
        getRequest.fetchSourceContext(fetchSourceContext);
        /***************指定获取数据源 end************/




        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);


        log.info("{}",getResponse.getSource());

    }



    @Test
    public void getRequestStoredTest() throws IOException {
        GetRequest request = new GetRequest("posts","2");
        request.storedFields("user");

        request.fetchSourceContext(FetchSourceContext.DO_NOT_FETCH_SOURCE);
        GetResponse getResponse = client.get(request, RequestOptions.DEFAULT);

        log.info("{}",getResponse.getSource());

        String message = getResponse.getField("user").getValue();
        log.info("{}",message);
    }


}
