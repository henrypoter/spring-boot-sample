package com.ypp.tunte.sample.wx.mp.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 公众号账户实体
 *
 * @author yanpp
 * @createDateTime 2020/3/12
 **/
@Entity
@Table(name="mp_account")
@Data
public class MpAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="app_id",length = 50)
    private String appId;
    @Column(name="secret",length = 255)
    private String secret;
    @Column(name="token",length = 100)
    private String token;
    @Column(name="aes_key",length = 255)
    private String aesKey;
    @Column(name="create_time")
    private LocalDateTime createTime;


}
