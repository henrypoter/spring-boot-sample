package com.ypp.tunte.sample.es.test.org;

import com.ypp.tunte.sample.es.test.SampleEsApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonMap;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/2
 **/
@Slf4j
public class UpdateRequestTest extends SampleEsApplicationTest {
    @Resource
    RestHighLevelClient client;

    @Test
    public void baseTest() throws IOException {
        UpdateRequest request = new UpdateRequest(
                "posts",  //索引
                "6");   //文档ID

        //使用脚本更新
        Map<String, Object> parameters = singletonMap("count", 4);
        Script inline = new Script(ScriptType.INLINE, "painless",
                "ctx._source.count += params.count", parameters);
        request.script(inline);

        //或者
        /*

        Script stored = new Script(
                ScriptType.STORED, null, "increment-field", parameters);
        request.script(stored);

         */

        UpdateResponse updateResponse = client.update(
                request, RequestOptions.DEFAULT);

        handleResponse(updateResponse);
    }

    @Test
    public void updatePartialDocTest() throws IOException {
        UpdateRequest request = new UpdateRequest("posts", "6");
        String jsonString = "{" +
                "\"updated\":\"2017-01-01\"," +
                "\"reason\":\"daily update\"" +
                "}";
        request.doc(jsonString, XContentType.JSON);

        UpdateResponse updateResponse = client.update(
                request, RequestOptions.DEFAULT);

        handleResponse(updateResponse);
    }

    @Test
    public void jsonMapTest() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("updated", new Date());
        jsonMap.put("reason", "daily update");
        UpdateRequest request = new UpdateRequest("posts", "4")
                .doc(jsonMap);
        UpdateResponse updateResponse = client.update(
                request, RequestOptions.DEFAULT);

        handleResponse(updateResponse);
    }

    @Test
    public void builderTest() throws IOException {

        /*
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.timeField("updated", new Date());
            builder.field("reason", "daily update");
        }
        builder.endObject();
        UpdateRequest request = new UpdateRequest("posts", "4")
                .doc(builder);

         */

        UpdateRequest request = new UpdateRequest("posts", "4")
                .doc("updated", new Date(),
                        "reason", "daily update one");

        UpdateResponse updateResponse = client.update(
                request, RequestOptions.DEFAULT);

        handleResponse(updateResponse);

    }

    /**
     * 更新或插入
     */
    @Test
    public void upsertTest() throws IOException {
        UpdateRequest request = new UpdateRequest("posts", "2");
        String jsonString = "{\"created\":\"2017-01-01\"}";
        request.upsert(jsonString, XContentType.JSON);
        UpdateResponse updateResponse = client.update(
                request, RequestOptions.DEFAULT);

        handleResponse(updateResponse);

    }




    private void handleResponse(UpdateResponse updateResponse){

        String index = updateResponse.getIndex();
        String id = updateResponse.getId();
        long version = updateResponse.getVersion();
        if (updateResponse.getResult() == DocWriteResponse.Result.CREATED) {
                log.info("处理首次创建文档的情况（upsert）");
        } else if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            log.info("处理文档更新的情况");
        } else if (updateResponse.getResult() == DocWriteResponse.Result.DELETED) {
            log.info("处理删除文档的情况");
        } else if (updateResponse.getResult() == DocWriteResponse.Result.NOOP) {
            log.info("处理文档不受更新影响的情况，即未对文档执行任何操作（空转）");
        }
    }

}
