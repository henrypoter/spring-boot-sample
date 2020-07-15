package com.ypp.tunte.sample.es.one.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/12
 **/
@Data
public class Bank implements Serializable {
    private String accountNumber;
    private double balance;
    private String firstname;
    private String lastname;
    private int age;
    private String gender;
    private String address;
    private String employer;
    private String email;
    private String city;
    private  String state;
}
