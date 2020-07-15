package com.ypp.tunte.sample.es.test.org;

import com.ypp.tunte.sample.es.test.SampleEsApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.ActiveShardCount;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/2
 **/
@Slf4j
public class BulkRequestTest extends SampleEsApplicationTest {
    @Resource
    RestHighLevelClient client;

    @Test
    public void baseTest() throws IOException {
        //创建BulkRequest
        BulkRequest request = new BulkRequest();

        //
        //将第一个IndexRequest添加到Bulk请求。 有关如何构建IndexRequest的更多信息，请参见Index API
        request.add(new IndexRequest("posts").id("1")
                .source(XContentType.JSON,"field", "foo"));
        request.add(new IndexRequest("posts").id("2")
                .source(XContentType.JSON,"field", "bar"));
        request.add(new IndexRequest("posts").id("3")
                .source(XContentType.JSON,"field", "baz"));

        request.timeout("2m");

        request.setRefreshPolicy("wait_for");

        request.waitForActiveShards(ActiveShardCount.ALL);

        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
        handleResponse(bulkResponse);
    }

    @Test
    public void baseTwoTest() throws IOException {

        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest("posts", "3"));
        request.add(new UpdateRequest("posts", "2")
                .doc(XContentType.JSON,"other", "test"));
        request.add(new IndexRequest("posts").id("4")
                .source(XContentType.JSON,"field", "baz"));
        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
        handleResponse(bulkResponse);
    }

    ActionListener<BulkResponse> listener = new ActionListener<BulkResponse>() {
        @Override
        public void onResponse(BulkResponse bulkResponse) {
            log.info("执行成功完成时调用");
            handleResponse(bulkResponse);
        }

        @Override
        public void onFailure(Exception e) {
            log.info("执行异常:{}",e.getMessage());
        }
    };

    @Test
    public void asyncTest() throws InterruptedException {
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest("posts").id("8")
                .source(XContentType.JSON,"field", "foo"));
        request.add(new IndexRequest("posts").id("9")
                .source(XContentType.JSON,"field", "bar"));
        request.add(new IndexRequest("posts").id("10")
                .source(XContentType.JSON,"field", "baz"));

        client.bulkAsync(request, RequestOptions.DEFAULT, listener);

        Thread.sleep(100);
    }

    private void handleResponse(BulkResponse bulkResponse ){
        if (bulkResponse.hasFailures()) {
                log.error("如果至少一个操作失败，则此方法返回true");
        }
        for (BulkItemResponse bulkItemResponse : bulkResponse) {

            if (bulkItemResponse.isFailed()) {
                BulkItemResponse.Failure failure = bulkItemResponse.getFailure();
                log.error("失败:{}",failure.getMessage());
                continue;
            }

            DocWriteResponse itemResponse = bulkItemResponse.getResponse();

            switch (bulkItemResponse.getOpType()) {
                case INDEX:
                case CREATE:
                    IndexResponse indexResponse = (IndexResponse) itemResponse;
                    break;
                case UPDATE:
                    UpdateResponse updateResponse = (UpdateResponse) itemResponse;
                    break;
                case DELETE:
                    DeleteResponse deleteResponse = (DeleteResponse) itemResponse;
            }
        }
    }

}
