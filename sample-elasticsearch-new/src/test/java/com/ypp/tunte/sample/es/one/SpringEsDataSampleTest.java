package com.ypp.tunte.sample.es.one;

import com.ypp.tunte.sample.es.one.pojo.Item;
import com.ypp.tunte.sample.es.one.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/5
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringEsDataSample.class)
@Slf4j
public class SpringEsDataSampleTest {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private RestHighLevelClient highLevelClient;

    @Autowired
    private ItemRepository itemRepository;




    @Test
    public void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("item");
        AcknowledgedResponse deleteIndexResponse = highLevelClient.indices().delete(request, RequestOptions.DEFAULT);
    }

    @Test
    public void create(){
        List<Item> list = new ArrayList<>();
        Item item = new Item(1L, "小米手机7", " 手机", "小米", 3499.00, "http://image.leyou.com/13123.jpg");
        list.add(item);
        list.add(new Item(2L, "坚果手机R1", " 手机", "锤子", 3699.00, "http://image.leyou.com/123.jpg"));
        list.add(new Item(3L, "华为META10", " 手机", "华为", 4499.00, "http://image.leyou.com/3.jpg"));
        this.itemRepository.saveAll(list);

    }

    @Test
    public void find() {
        Optional<Item> item = this.itemRepository.findById(1L);
        System.out.println("item.get() = " + item.get());
    }

    @Test
    public void findAllSort() {
        Iterable<Item> items = this.itemRepository.findAll(Sort.by("price").descending());

        items.forEach(System.out::println);
    }

    @Test
    public void findByTitle() {
        Iterable<Item> items = this.itemRepository.findByTitle("手机");
        items.forEach(System.out::println);
    }

    /**
     * 查询价格区间
     */
    @Test
    public void findByPrice() {
        List<Item> byPriceBetween = this.itemRepository.findByPriceBetween(3699d, 4499d);
        byPriceBetween.forEach(System.out::println);
    }

    /**
     * 添加测试数据
     */
    @Test
    public void indexList() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(1L, "小米手机7", "手机", "小米", 3299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(2L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(3L, "华为META10", "手机", "华为", 4499.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(4L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(5L, "荣耀V10", "手机", "华为", 2799.00, "http://image.leyou.com/13123.jpg"));
        // 接收对象集合，实现批量新增
        itemRepository.saveAll(list);
    }

    /**
     * 通过标题查询
     */
    @Test
    public void testSearch() {

        //执行查询
        Iterable<Item> items = this.itemRepository.findByTitle("手机");
        items.forEach(System.out::println);
    }

    @Test
    public void findByCategoryTest() throws ExecutionException, InterruptedException {
        //Future<Item> future = this.itemRepository.findByCategory("手机");
        //log.info("{}",future.get());
        //log.info("执行完成");
    }

    @Test
    public void findOneByTitleTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Item> completableFuture = this.itemRepository.findOneByTitle("手机");
        log.info("{}",completableFuture.get());
    }

    @Test
    public void findOneByCategoryTest(){
        ListenableFuture<List<Item>> task = this.itemRepository.findByCategory("手机");

        task.addCallback((items)->{
            items.forEach(item -> {
                log.info("{}",item);
            });

        },(error)->{
            log.error("error:{}",error.getMessage());
        });
        log.info("aaaa");

    }

}