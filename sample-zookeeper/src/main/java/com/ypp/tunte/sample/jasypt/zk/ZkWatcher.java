package com.ypp.tunte.sample.jasypt.zk;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/2/24
 **/
public class ZkWatcher implements Watcher {

    /**
     * 集群地址
     */
    private static final String CONNECT_ADDRES = "192.168.2.107:2181";
    private static ZooKeeper zooKeeper;

    /**
     * 超时时间
     */
    private static final int SESSIONTIME = 20000;

    public ZkWatcher() throws IOException {
        createConnection(CONNECT_ADDRES, SESSIONTIME);
    }


    @Override
    public void process(WatchedEvent event) {
        Event.KeeperState keeperState = event.getState();
        // 事件类型
        Event.EventType eventType = event.getType();
        // 节点名称
        String path = event.getPath();
        System.out.println(
                "#####process()####调用####keeperState:" + keeperState + ",eventType:" + eventType + ",path:" + path);
        if (Event.KeeperState.SyncConnected == keeperState) {
            // 连接类型
            if (Event.EventType.None == eventType) {
                // 建立zk连接
                System.out.println("建立zk连接成功!");
            }
            // 创建类型
            if (Event.EventType.NodeCreated == eventType) {
                System.out.println("####事件通知,当前创建一个新的节点####路径:" + path);
            }
            // 修改类型
            if (Event.EventType.NodeDataChanged == eventType) {
                System.out.println("####事件通知,当前创建一个修改节点####路径:" + path);
            }
            // 删除类型
            if (Event.EventType.NodeDeleted == eventType) {
                System.out.println("####事件通知,当前创建一个删除节点####路径:" + path);
            }
        }
        System.out.println("####################################################");
        System.out.println();
    }




    private void createConnection(String connectAddres, int sessiontime) throws IOException {
        zooKeeper = new ZooKeeper(connectAddres,sessiontime,this);
    }

    // 创建节点
    public void createNode(String path, String data) {
        try {
            String result = zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("创建节点成功....result:" + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 修改节点
    public void updateNode(String path, String data) {
        try {
            zooKeeper.setData(path, data.getBytes(), -1);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // 删除节点
    public void deleNode(String path) {
        try {
            zooKeeper.delete(path, -1);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void close() {
        try {
            if (zooKeeper != null) {
                zooKeeper.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZkWatcher zkWatcher = new ZkWatcher();
        String path = "/parent1";
        zkWatcher.createNode(path,"aaaa parent");
        zooKeeper.exists(path, true);
        zkWatcher.close();
    }


}
