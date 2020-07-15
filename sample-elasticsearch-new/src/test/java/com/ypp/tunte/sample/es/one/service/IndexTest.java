package com.ypp.tunte.sample.es.one.service;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import java.io.IOException;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/1
 **/
@Slf4j
public class IndexTest extends TestBase {



    @Test
    public void firstTest() throws IOException {

        IndexRequest request = new IndexRequest("customer");
        request.id("2");
        String jsonString = "{" +
                "\"name\": \"Tom kitty\""+
                "}";
        request.source(jsonString, XContentType.JSON);

        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        responseHandle(indexResponse);
    }


    private void responseHandle( IndexResponse indexResponse){
        String index = indexResponse.getIndex();
        String id = indexResponse.getId();
        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
                log.info("首次创建索引---{}",index);
        } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
                log.info("已存在的文档被重写---索引 = {}，ID = {},",index,id);
        }
        ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
                log.info("成功分片数量少于总分片数量");
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure :
                    shardInfo.getFailures()) {
                String reason = failure.reason();
                log.info("索引:{},失败原因---{}",index,reason);
            }
        }
    }

}
