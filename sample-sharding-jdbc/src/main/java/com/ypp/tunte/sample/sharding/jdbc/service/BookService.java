package com.ypp.tunte.sample.sharding.jdbc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ypp.tunte.sample.sharding.jdbc.entity.Book;

import java.util.List;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/23
 **/
public interface BookService extends IService<Book> {

    /**
     * 保存书籍信息
     *
     * @param book
     * @return
     */
    boolean save(Book book,boolean transactionTest);

    /**
     * 查询全部书籍信息
     *
     * @return
     */
    List<Book> getBookList();
}