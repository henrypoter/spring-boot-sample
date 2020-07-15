package com.ypp.tunte.sample.sb.es.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/17
 **/
@NoRepositoryBean
public interface MyBaseRepository<T,ID> extends Repository<T,ID> {
    Optional<T> findById(ID id);

    <S extends T> S save(S entity);
}
