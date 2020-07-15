package com.ypp.tunte.sample.es.one.repository;

import com.ypp.tunte.sample.es.one.SpringEsDataSample;
import com.ypp.tunte.sample.es.one.pojo.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/5
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringEsDataSample.class)
@Slf4j
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void addTest(){
        Product product = new Product(1L,"ELCN-11167-IEIE","测试产品1",30.88, new Date());
        productRepository.save(product);
    }

    @Test
    public void selectTest(){
       Optional<Product> optionalProduct = productRepository.findById(1L);
       if (optionalProduct.isPresent()){
           log.info("{}",optionalProduct.get());
       }
    }

}