package com.ypp.tunte.sample.sharding.jdbc.controller;

import cn.hutool.core.date.DateUtil;
import com.ypp.tunte.sample.sharding.jdbc.entity.Book;
import com.ypp.tunte.sample.sharding.jdbc.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/23
 **/
@RestController
@Slf4j
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public List<Book> getItems(){
        return bookService.getBookList();
    }

    @RequestMapping(value = "/book",method = RequestMethod.POST)
    public Boolean saveItem(Book book){
        log.info("count % 3 = {}",book.getCount()%3);
        book.setPublishDate(new Date());
        return bookService.save(book,true);
    }
}