package com.ypp.tunte.sample.jasypt.kafka.service.impl;

import com.ypp.tunte.sample.jasypt.kafka.model.KyhkArticle;
import com.ypp.tunte.sample.jasypt.kafka.model.MysqlBinlog;
import com.ypp.tunte.sample.jasypt.kafka.service.ISyncMysqlEsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/19
 **/
@Service
@Slf4j
public class SyncMysqlEsServiceImpl implements ISyncMysqlEsService {
    @Override
    public boolean syncFull() {
        return false;
    }

    @Override
    public boolean syncIncrement(String indexName, Object o) {

        log.info("同步文章数据:{}",o);

        return false;
    }
}
