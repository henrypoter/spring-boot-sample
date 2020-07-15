package com.ypp.tunte.sample.es.test.org;

import com.ypp.tunte.sample.es.test.SampleEsApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/2
 **/
@Slf4j
public class SearchRequestTest extends SampleEsApplicationTest {
    @Resource
    RestHighLevelClient client;

    @Test
    public void baseTest() throws IOException {
        SearchRequest searchRequest = new SearchRequest("posts");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        handleSearchResponse(searchResponse);
    }

    @Test
    public void builderTest() throws IOException {
        SearchRequest searchRequest = new SearchRequest("bank");

        //使用默认选项创建SearchSourceBuilder
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //设置查询。 可以是任何类型的QueryBuilder
        sourceBuilder.query(QueryBuilders.fuzzyQuery("address","Lane"));
        //设置确定开始搜索的结果索引的from选项。 预设为0。
        sourceBuilder.from(0);
        //设置大小选项，该选项确定要返回的搜索命中数。 默认为10
        sourceBuilder.size(5);
        //设置一个可选的超时时间，以控制允许搜索的时间。
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        handleSearchResponse(searchResponse);

    }

    @Test
    public void buildingQueryTest() throws IOException {

        SearchRequest searchRequest = new SearchRequest("bank");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("state", "HI");
        //对匹配查询启用模糊匹配
        matchQueryBuilder.fuzziness(Fuzziness.AUTO);
        //在匹配查询中设置前缀长度选项
        matchQueryBuilder.prefixLength(3);
        //设置最大扩展选项以控制查询的模糊过程
        matchQueryBuilder.maxExpansions(10);

        /*
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("firstname", "Levine")
                .fuzziness(Fuzziness.AUTO)
                .prefixLength(3)
                .maxExpansions(10);
         */


        sourceBuilder.query(matchQueryBuilder);


        sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        sourceBuilder.sort(new FieldSortBuilder("age").order(SortOrder.ASC));


        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        handleSearchResponse(searchResponse);

    }

    @Test
    public void partialQueryTest() throws IOException {
        SearchRequest searchRequest = new SearchRequest("bank");
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("state", "HI")
                .fuzziness(Fuzziness.AUTO)
                .prefixLength(3)
                .maxExpansions(10);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(matchQueryBuilder);


        /*********包括部分*********/
        String[] includeFields = new String[] {"address", "account_*"};
        String[] excludeFields = new String[] {"email"};
        sourceBuilder.fetchSource(includeFields, excludeFields);
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        handleSearchResponse(searchResponse);
    }


    @Test
    public void highlightingTest() throws IOException {
        SearchRequest searchRequest = new SearchRequest("bank");
        QueryBuilder matchQueryBuilder = QueryBuilders.fuzzyQuery("address","Avenue")
                .fuzziness(Fuzziness.AUTO);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(matchQueryBuilder);

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field highlightTitle = new HighlightBuilder.Field("lastname");
        highlightTitle.highlighterType("unified");
        highlightBuilder.field(highlightTitle);
        HighlightBuilder.Field highlightUser = new HighlightBuilder.Field("firstname");
        highlightBuilder.field(highlightUser);
        sourceBuilder.highlighter(highlightBuilder);


        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        handleSearchResponse(searchResponse);

    }


    @Test
    public void aggregationTest() throws IOException {
        SearchRequest searchRequest = new SearchRequest("bank");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.fetchSource(false);

        TermsAggregationBuilder aggregation = AggregationBuilders.terms("by_state").field("stateggg");
        aggregation.subAggregation(AggregationBuilders.avg("average_balance").field("balance"));
        sourceBuilder.aggregation(aggregation);


        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        handleSearchResponse(searchResponse);

    }


    private void handleSearchResponse(SearchResponse searchResponse){
        RestStatus status = searchResponse.status();
        TimeValue took = searchResponse.getTook();
        Boolean terminatedEarly = searchResponse.isTerminatedEarly();
        boolean timedOut = searchResponse.isTimedOut();

        log.info("status:{},took:{},terminatedEarly:{},timedOut:{}",status,took,terminatedEarly,timedOut);


        int totalShards = searchResponse.getTotalShards();
        int successfulShards = searchResponse.getSuccessfulShards();
        int failedShards = searchResponse.getFailedShards();
        log.info("totalShards:{},successfulShards:{},failedShards:{}",totalShards,successfulShards,failedShards);
        for (ShardSearchFailure failure : searchResponse.getShardFailures()) {
            // failures should be handled here
            log.error("失败:{}",failure);
        }


        SearchHits hits = searchResponse.getHits();
        log.info("hits:{}",hits);


        TotalHits totalHits = hits.getTotalHits();
// the total number of hits, must be interpreted in the context of totalHits.relation
        long numHits = totalHits.value;
// whether the number of hits is accurate (EQUAL_TO) or a lower bound of the total (GREATER_THAN_OR_EQUAL_TO)
        TotalHits.Relation relation = totalHits.relation;
        float maxScore = hits.getMaxScore();

        log.info("numHits:{},maxScore:{}",numHits,maxScore);


        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            // do something with the SearchHit
            log.info("hit:{}",hit);
        }



    }
}
