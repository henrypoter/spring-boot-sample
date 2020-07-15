package com.ypp.tunte.sample.wx.mp.repository;

import com.ypp.tunte.sample.wx.mp.entity.MpAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/12
 **/
@Repository
public interface MpAccountRepository extends JpaRepository<MpAccount,Integer> {

}
