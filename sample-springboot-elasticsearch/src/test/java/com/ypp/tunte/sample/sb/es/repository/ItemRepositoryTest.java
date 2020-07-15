package com.ypp.tunte.sample.sb.es.repository;

import com.ypp.tunte.sample.sb.es.EsSampleApplicationTest;
import com.ypp.tunte.sample.sb.es.entity.Item;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/17
 **/
public class ItemRepositoryTest extends EsSampleApplicationTest {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void addIndexTest() {
        elasticsearchRestTemplate.save(new Item(1L,"红米8","手机","小米",1800.00,"http://image.baidu.com/3456435.jpg"));
    }

    @Test
    public void delIndexTest() {
        elasticsearchRestTemplate.indexOps(Item.class).delete();
    }

    @Test
    public void insertTest() {
        Item item = new Item(2L, "坚果R1", "手机", "锤子", 2500.00, "http://image.baidu.com/13123.jpg");
        //Order order = new Order(20180020,"菜单");
        itemRepository.save(item);
    }

    @Test
    public void insertListTest() {
        List<Item> list = new LinkedList<>();
        list.add(new Item(9L, "华为p20", "手机", "华为", 3500.00, "http://image.baidu.com/13123.jpg"));
        list.add(new Item(10L, "华为p30", "手机", "华为", 5450.00, "http://image.baidu.com/13123.jpg"));
        list.add(new Item(11L, "华为p30 pro", "手机", "华为", 6980.00, "http://image.baidu.com/13123.jpg"));
        itemRepository.saveAll(list);
    }

    @Test
    public void queryAllTest() {
        Iterable<Item> items = this.itemRepository.findAll(Sort.by("price").ascending());
        Iterator<Item> iterator = items.iterator();
        for (Item item : items) {
            System.out.println(item);
        }
    }


    @Test
    public void findByTitle() {
        Item item = this.itemRepository.findByTitle("坚果R1");
        System.out.println(item);
    }

    @Test
    public void findByPriceBetween() {
        List<Item> list = this.itemRepository.findByPriceBetween(2000.00, 3500.00);
        list.forEach(item -> {
            System.out.println("item = " + item);
        });
    }

    @Test
    public void findByTitleLike() {

        List<Item> list = this.itemRepository.findByTitleLike("P30");
        list.forEach(item -> {
            System.out.println("item = " + item);
        });

    }

    @Test
    public void matchQuery(){



    }

}