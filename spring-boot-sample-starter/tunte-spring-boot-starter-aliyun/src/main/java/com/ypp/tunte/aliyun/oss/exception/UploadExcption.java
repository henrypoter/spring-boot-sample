package com.ypp.tunte.aliyun.oss.exception;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/17
 **/
public class UploadExcption extends RuntimeException {
    public UploadExcption(){
        super();
    }
    public UploadExcption(String message){
        super(message);
    }

}
