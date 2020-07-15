package com.ypp.tunte.sample.jasypt.kafka.service;

import com.ypp.tunte.sample.jasypt.kafka.model.MysqlBinlog;

/**
 * mysql与ES同步
 *
 * @author yanpp
 * @createDateTime 2020/6/19
 **/
public interface ISyncMysqlEsService {

    /**
     * 全量同步
     * @return
     */
    boolean syncFull();

    /**
     * 增量同步
     * @return
     */
    boolean syncIncrement(String indexName, Object obj);

}
