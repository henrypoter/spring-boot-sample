package com.ypp.tunte.sample.sharding.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/26
 **/
@Slf4j
public class MyTest {
    @Test
    public void test(){
        Long i = 1254307305474834454L;
        log.info("{} % 3 = {}",i,i%3);

       log.info ("{}",(i/ 1) % 10);
    }
}
