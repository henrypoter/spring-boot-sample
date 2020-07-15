package com.ypp.tunte.sample.es.test.org;

import com.ypp.tunte.sample.es.test.SampleEsApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/1
 **/
@Slf4j
public class IndexTest extends SampleEsApplicationTest {

    @Resource
    RestHighLevelClient client;


    /**
     * json模式
     * @throws IOException
     */
    @Test
    public void jsonTextTest() throws IOException {

        IndexRequest request = new IndexRequest("customer");
        request.id("2");
        String jsonString = "{" +
                "\"name\": \"Tom kitty\""+
                "}";
        request.source(jsonString, XContentType.JSON);

        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        responseHandle(indexResponse);
    }

    @Test
    public void jsonMapTest() throws IOException {
        Map<String,Object> jsonMap = new HashMap<>(2);
        jsonMap.put("user","kimchy");
        jsonMap.put("postDate",new Date());
        jsonMap.put("message","trying out Elasticsearch");
        IndexRequest indexRequest = new IndexRequest("posts").id("id").source(jsonMap);
        IndexResponse indexResponse =   client.index(indexRequest,RequestOptions.DEFAULT);
        responseHandle(indexResponse);
    }

    @Test
    public void buildTest() throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("user","iller");
            builder.timeField("postData",new Date());
            builder.field("message","trying out Elasticsearch");
            builder.field("count","1");
        }
        builder.endObject();

        IndexRequest indexRequest = new IndexRequest("posts").id("6").source(builder);

        IndexResponse indexResponse =   client.index(indexRequest,RequestOptions.DEFAULT);
        responseHandle(indexResponse);


    }

    @Test
    public void  asynIndexRequestTest() throws IOException, InterruptedException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("user","sun");
            builder.timeField("postData",new Date());
            builder.field("message","trying out Elasticsearch");
        }
        builder.endObject();

        IndexRequest indexRequest = new IndexRequest("posts").id("5").source(builder);

        ActionListener<IndexResponse> listener = new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                responseHandle(indexResponse);
            }

            @Override
            public void onFailure(Exception e) {
                log.error("创建索引请求错误,{}",e.getMessage());
            }
        };

        client.indexAsync(indexRequest,RequestOptions.DEFAULT,listener);

        Thread.sleep(2000);
    }



    private void responseHandle( IndexResponse indexResponse){
        String index = indexResponse.getIndex();
        String id = indexResponse.getId();
        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
            log.info("首次创建索引---索引名:{},ID：{}",index,id);
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
