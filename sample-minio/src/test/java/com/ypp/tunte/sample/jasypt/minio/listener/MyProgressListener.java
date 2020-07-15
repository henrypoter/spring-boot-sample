package com.ypp.tunte.sample.jasypt.minio.listener;

import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/2/28
 **/
public class MyProgressListener implements ProgressListener {
    @Override
    public void progressChanged(ProgressEvent progressEvent) {
        System.out.println("传输字节: " + progressEvent.getBytesTransferred());
    }
}
