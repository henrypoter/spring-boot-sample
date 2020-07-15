package com.ypp.tunte.sample.sharding.jdbc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypp.tunte.sample.sharding.jdbc.entity.Address;
import com.ypp.tunte.sample.sharding.jdbc.mapper.AddressMapper;
import com.ypp.tunte.sample.sharding.jdbc.service.AddressService;
import org.springframework.stereotype.Service;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/26
 **/
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {
    @Override
    public void create(Address address) {
        this.save(address);
    }
}
