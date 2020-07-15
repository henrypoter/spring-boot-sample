package com.ypp.tunte.sample.sb.es.repository;

import com.ypp.tunte.sample.sb.es.entity.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/17
 **/
public interface ItemRepository extends ElasticsearchRepository<Item,Long> {

    Item findByTitle(String title);

    /**
     * 根据价格区间查询
     * @param from
     * @param to
     * @return
     */
    List<Item> findByPriceBetween(Double from,Double to);

    /**
     * 标题模糊查询
     * @param title
     * @return
     */
    List<Item> findByTitleLike(String title);

}
