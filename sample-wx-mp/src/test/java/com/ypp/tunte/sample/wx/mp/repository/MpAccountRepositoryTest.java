package com.ypp.tunte.sample.wx.mp.repository;

import com.ypp.tunte.sample.wx.mp.entity.MpAccount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MpAccountRepositoryTest {

    @Autowired
    private MpAccountRepository mpAccountRepository;

    @Before
    public void before(){
        MpAccount mpAccount=new MpAccount();
        mpAccount.setAppId("wxee589b08c4bf4d45");
        mpAccount.setSecret("2680a61ca8fa717caf319212b5dde182");
        mpAccount.setToken("keyu4cloud");
        mpAccount.setAesKey("keyu4cloud");
        mpAccount.setCreateTime(LocalDateTime.now());
        mpAccountRepository.save(mpAccount);
    }

    @Test
    public void queryAllTest(){
       List<MpAccount> list = mpAccountRepository.findAll();
        Assert.assertEquals(1,list.size());
    }

}