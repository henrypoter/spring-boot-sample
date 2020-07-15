package com.ypp.tunte.sample.sharding.jdbc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ypp.tunte.sample.sharding.jdbc.entity.Address;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/26
 **/
public interface AddressService extends IService<Address> {

    void create(Address address);
}
