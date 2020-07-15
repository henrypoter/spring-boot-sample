package com.ypp.tunte.sample.sharding.jdbc.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypp.tunte.sample.sharding.jdbc.entity.Address;
import com.ypp.tunte.sample.sharding.jdbc.entity.Book;
import com.ypp.tunte.sample.sharding.jdbc.entity.User;
import com.ypp.tunte.sample.sharding.jdbc.mapper.BookMapper;
import com.ypp.tunte.sample.sharding.jdbc.service.AddressService;
import com.ypp.tunte.sample.sharding.jdbc.service.BookService;
//import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
//import org.apache.shardingsphere.transaction.core.TransactionType;
import com.ypp.tunte.sample.sharding.jdbc.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/23
 **/
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Resource
    private AddressService addressService;

    @Resource
    private UserService userService;

    @Override
    public List<Book> getBookList() {
        return baseMapper.selectList(Wrappers.<Book>lambdaQuery());
    }


    @Override
    @Transactional
    //@ShardingTransactionType(TransactionType.XA)  // 支持TransactionType.LOCAL, TransactionType.XA, TransactionType.BASE
    public boolean save(Book book,boolean transactionTest) {
/*        Address address = new Address();
        address.setAddressName(RandomUtil.randomString(6));
        address.setCreateTime(LocalDateTime.now());
        addressService.save(address);


        User user=new User();
        user.setUserName(RandomUtil.randomString(4));
        user.setPwd(RandomUtil.randomString(10));
        user.setAssistedQueryPwd(RandomUtil.randomString(12));
        user.setUserNamePlain(RandomUtil.randomString(3));
        userService.save(user);*/

        super.save(book);
        return true;

    }
}