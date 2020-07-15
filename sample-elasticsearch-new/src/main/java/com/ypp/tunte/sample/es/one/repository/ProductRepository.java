package com.ypp.tunte.sample.es.one.repository;

import com.ypp.tunte.sample.es.one.pojo.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/5
 **/
public interface ProductRepository extends ElasticsearchRepository<Product,Long> {
}
