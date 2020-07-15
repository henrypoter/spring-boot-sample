package com.ypp.tunte.prometheus.reposity;

import com.ypp.tunte.prometheus.entity.Hello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/7/7
 **/
@Repository
public interface HelloRepository extends JpaRepository<Hello,Integer> {
}
