package com.ypp.tunte.sample.jasypt.zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/2/24
 **/
public class Hello {
    private static final String CONNECT_ADDRES = "192.168.56.70:2181";

    private static final int SESSIONTIME = 2000;

    private static final CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zk = new ZooKeeper(CONNECT_ADDRES, SESSIONTIME, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                Event.KeeperState keeperState = event.getState();
                Event.EventType tventType = event.getType();
                if (Event.KeeperState.SyncConnected==keeperState){
                    if (Event.EventType.None == tventType){
                        countDownLatch.countDown();
                        System.out.println("zk 建立连接");
                    }
                }
            }
        });

        // 进行阻塞
        countDownLatch.await();
        String result = zk.create("/testRott/children", "children 12245465".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        System.out.println("result:" + result);
        zk.close();

    }

}
