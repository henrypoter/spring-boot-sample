package com.ypp.tunte.sample.es.test.org;

import com.ypp.tunte.sample.es.test.SampleEsApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.GetSourceRequest;
import org.elasticsearch.client.core.GetSourceResponse;
import org.elasticsearch.common.Strings;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/1
 **/
@Slf4j
public class GetSourceTest extends SampleEsApplicationTest {

    @Resource
    RestHighLevelClient client;

    @Test
    public void simpleTest() throws IOException {

        GetSourceRequest getSourceRequest = new GetSourceRequest(      "posts",          "2");

        GetSourceResponse response = client.getSource(getSourceRequest, RequestOptions.DEFAULT);
        log.info("数据信息:{}",response.getSource());
    }

    @Test
    public void selectFieldTest() throws IOException {
        GetSourceRequest getSourceRequest = new GetSourceRequest(      "posts",          "2");
        String[] includes = Strings.EMPTY_ARRAY;
        String[] excludes = new String[]{"postData"};
        getSourceRequest.fetchSourceContext(
                new FetchSourceContext(true, includes, excludes));

        GetSourceResponse response = client.getSource(getSourceRequest, RequestOptions.DEFAULT);
        log.info("数据信息:{}",response.getSource());
    }

    @Test
    public void asynTest() throws InterruptedException {
        GetSourceRequest getSourceRequest = new GetSourceRequest(      "posts",          "2");
        client.getSourceAsync(getSourceRequest,RequestOptions.DEFAULT,listener);

        Thread.sleep(100);
    }

    ActionListener<GetSourceResponse> listener =
            new ActionListener<GetSourceResponse>() {
                @Override
                public void onResponse(GetSourceResponse getResponse) {
                    log.info("获取数据源成功:{}",getResponse.getSource());
                }

                @Override
                public void onFailure(Exception e) {
                    log.error("获取数据源失败: {}",e.getMessage());
                }
            };

}
