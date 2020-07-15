package com.ypp.tunte.sample.sb.es;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.ypp.tunte.sample.sb.es.entity.KyhkArt;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.HighlightQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;

import java.util.List;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/12
 **/
@Slf4j
public class KyhkArtServiceTest extends EsSampleApplicationTest {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 创建索引
     */
    @Test
    public void createIndexTest(){
     boolean result = elasticsearchRestTemplate.indexOps(KyhkArt.class).create();
        Assert.assertTrue(result);
    }

    /**
     *
     */
    @Test
    public void deleteIndexTest() {
        boolean result = elasticsearchRestTemplate.indexOps(KyhkArt.class).delete();
        Assert.assertTrue(result);
    }

    @Test
    public void addDocumentTest(){
        elasticsearchRestTemplate.save(new KyhkArt("hello","hello art",0));
    }

    @Test
    public void batchAddDocumentTest(){
        List<KyhkArt> list = Lists.newArrayList(
                new KyhkArt("对象/文件（Object）","对象是OSS存储数据的基本单元，也被称为OSS的文件。对象由元信息（Object Meta），用户数据（Data）和文件名（Key）组成。对象由存储空间内部唯一的Key来标识。对象元信息是一组键值对，表示了对象的一些属性，比如最后修改时间、大小等信息，同时用户也可以在元信息中存储一些自定义的信息。\n" +
                        "\n" +
                        "对象的生命周期是从上传成功到被删除为止。在整个生命周期内，只有通过追加上传的Object可以继续通过追加上传写入数据，其他上传方式上传的Object内容无法编辑，您可以通过重复上传同名的对象来覆盖之前的对象。\n" +
                        "\n" +
                        "对象的命名规范如下：\n" +
                        "\n" +
                        "使用UTF-8编码。\n" +
                        "长度必须在1~1023字节之间。\n" +
                        "不能以正斜线（/）或者反斜线（\\）开头。",6),
                new KyhkArt("ObjectKey","在各语言SDK中，ObjectKey、Key以及ObjectName是同一概念，均表示对Object执行相关操作时需要填写的Object名称。例如向某一存储空间上传Object时，ObjectKey表示上传的Object所在存储空间的完整名称，即包含文件后缀在内的完整路径，如填写为abc/efg/123.jpg。",6),
                new KyhkArt("地域","Region表示OSS的数据中心所在物理位置。用户可以根据费用、请求来源等选择合适的地域创建Bucket。一般来说，距离用户更近的Region访问速度更快。详情请查看 OSS 已经开通的 Region。\n" +
                        "\n" +
                        "Region是在创建Bucket的时候指定的，一旦指定之后就不允许更改。该Bucket下所有的Object都存储在对应的数据中心，目前不支持Object级别的Region设置。",6),
                new KyhkArt("访问域名","Endpoint表示OSS对外服务的访问域名。OSS以HTTP RESTful API的形式对外提供服务，当访问不同的Region的时候，需要不同的域名。通过内网和外网访问同一个Region所需要的Endpoint也是不同的。例如杭州Region的外网Endpoint是oss-cn-hangzhou.aliyuncs.com，内网Endpoint是oss-cn-hangzhou-internal.aliyuncs.com。具体的内容请参见各个 Region 对应的 Endpoint。",6),
                new KyhkArt("访问密钥","AccessKey（简称AK）指的是访问身份验证中用到的AccessKeyId和AccessKeySecret。OSS通过使用AccessKeyId和AccessKeySecret对称加密的方法来验证某个请求的发送者身份。AccessKeyId用于标识用户；AccessKeySecret是用户用于加密签名字符串和OSS用来验证签名字符串的密钥，必须保密。对于OSS来说，AccessKey的来源有：\n" +
                        "\n" +
                        "Bucket的拥有者申请的AccessKey。\n" +
                        "被Bucket的拥有者通过RAM授权给第三方请求者的AccessKey。\n" +
                        "被Bucket的拥有者通过STS授权给第三方请求者的AccessKey。",6)
        );
        elasticsearchRestTemplate.save(list);
    }

    @Test
    public void queryTest(){
        Query query = new StringQuery("hello");

        KyhkArt result =  elasticsearchRestTemplate.get("TSNzp3IBL1f6rX8CA9gi",KyhkArt.class);
        System.out.println(result);
    }

    @Test
    public void queryAllTest(){
        SearchHits<KyhkArt> searchHits =  elasticsearchRestTemplate.search(Query.findAll(),   KyhkArt.class);
        searchHits.getSearchHits().forEach(hit ->{
            System.out.println(hit);
        });
    }

    /**
     *
     */
    @Test
    public void queryMatchTest(){
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("title","欠费");
        Query query =new StringQuery(queryBuilder.toString());
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.preTags("<span style=\"color:red\">").postTags("</span>");
        query.setHighlightQuery(new HighlightQuery(highlightBuilder));


        SearchHits<KyhkArt> searchHits =  elasticsearchRestTemplate.search(query,   KyhkArt.class);
        searchHits.getSearchHits().forEach(hit ->{
            log.info("实体:{},高亮:{}",hit.getContent(), StrUtil.join(",",hit.getHighlightField("title")));
        });
    }

    @Test
    public void queryTermAndPageTest(){
        QueryBuilder queryBuilder = QueryBuilders.termQuery("click","6");
        Query query =new StringQuery(queryBuilder.toString());

        /**
         * 分页设置
         */
        query.setPageable(PageRequest.of(0,2));

        SearchHits<KyhkArt> searchHits =  elasticsearchRestTemplate.search(query,   KyhkArt.class);
        searchHits.getSearchHits().forEach(hit ->{
            log.info("实体:{},高亮:{}",hit.getContent(), StrUtil.join(",",hit.getHighlightField("title")));
        });
    }

    @Test
    public void queryBoolTest(){
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("context","对象"));
        Query query =new StringQuery(queryBuilder.toString());
        SearchHits<KyhkArt> searchHits =  elasticsearchRestTemplate.search(query,   KyhkArt.class);
        searchHits.getSearchHits().forEach(hit ->{
            log.info("实体:{},高亮:{}",hit.getContent(), StrUtil.join(",",hit.getHighlightField("title")));
        });
    }

}
