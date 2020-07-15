package com.ypp.tunte.sample.sb.es.repository;

import com.ypp.tunte.sample.sb.es.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/16
 **/
public interface BookRepository extends ElasticsearchRepository<Book,String> {
}
