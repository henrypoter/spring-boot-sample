package com.ypp.tunte.sample.es.one.repository;

import com.ypp.tunte.sample.es.one.pojo.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/5
 **/
public interface ItemRepository extends ElasticsearchRepository<Item,Long> {
    Iterable<Item> findByTitle(String title);

    List<Item> findByPriceBetween(double rangeMin, double rangeMax);



    @Async
    CompletableFuture<Item> findOneByTitle(String title);

    @Async
    ListenableFuture<List<Item>> findByCategory(String category);
}
