package com.ypp.tunte.sample.es.test.org;

import com.ypp.tunte.sample.es.test.SampleEsApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.VersionType;
import org.elasticsearch.rest.RestStatus;
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
public class DeleteRequestTest extends SampleEsApplicationTest {
    @Resource
    RestHighLevelClient client;

    /**
     * 同步请求
     * @throws IOException
     */
    @Test
    public void deleteRequestTest() throws IOException {

        DeleteRequest request = new DeleteRequest(
                "posts",//索引
                "id");//文档ID

        //可选参数
        request.routing("routing");

        request.timeout(TimeValue.timeValueMinutes(2));//超时两分钟
        //或者
        //request.timeout("2m");

        //将策略刷新为WriteRequest.RefreshPolicy实例
        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        //或,等待主分片作为字符串可用的超时
        //request.setRefreshPolicy("wait_for");

        //版本
        request.version(2);

        //版本类型
        request.versionType(VersionType.EXTERNAL);

        /*******同步执行*******/
        DeleteResponse deleteResponse = client.delete(request, RequestOptions.DEFAULT);

        handleResponse(deleteResponse);
    }

    /**
     * 异步执行
     * @throws InterruptedException
     */
    @Test
    public void asynDeleteRequestTest() throws InterruptedException {
        DeleteRequest request = new DeleteRequest(
                "posts",
                "5");
        client.deleteAsync(request, RequestOptions.DEFAULT, listener);
        Thread.sleep(100);

    }

    ActionListener<DeleteResponse> listener = new ActionListener<DeleteResponse>() {
        @Override
        public void onResponse(DeleteResponse deleteResponse) {
                handleResponse(deleteResponse);
        }

        @Override
        public void onFailure(Exception e) {
            log.error("删除请求异常:{}",e.getMessage());
        }
    };


    @Test
    public void exceptionExample() throws IOException {
        DeleteRequest request = new DeleteRequest("posts", "does_not_exist");
        DeleteResponse deleteResponse = client.delete(
                request, RequestOptions.DEFAULT);
        if (deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND) {
            log.error("如果找不到要删除的文档，请执行某些操作");
        }
    }

    @Test
    public void exceptionExampleTwo(){
        //如果存在版本冲突，将抛出ElasticsearchException
        try {
            DeleteResponse deleteResponse = client.delete(
                    new DeleteRequest("posts", "4").setIfSeqNo(100).setIfPrimaryTerm(2),
                    RequestOptions.DEFAULT);
            handleResponse(deleteResponse);
        } catch (ElasticsearchException exception) {
            if (exception.status() == RestStatus.CONFLICT) {
                log.error("引发的异常表明已返回版本冲突错误");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void handleResponse(DeleteResponse deleteResponse){
        String index = deleteResponse.getIndex();
        String id = deleteResponse.getId();
        long version = deleteResponse.getVersion();



        ReplicationResponse.ShardInfo shardInfo = deleteResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
                log.info("处理成功分片数量少于总分片数量的情况");
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure :
                    shardInfo.getFailures()) {
                String reason = failure.reason();
                log.error("处理潜在的异常:{}",reason);
            }
        }
    }



}
