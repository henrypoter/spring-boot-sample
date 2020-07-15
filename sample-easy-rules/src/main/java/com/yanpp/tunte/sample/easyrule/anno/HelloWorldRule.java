package com.yanpp.tunte.sample.easyrule.anno;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/20
 **/
@Rule(name = "Hello World rule",description = "Always say hello world")
public class HelloWorldRule {

    @Condition
    public boolean when(){
        return true;
    }

    @Action
    public void then(){
        System.out.println("hello world");
    }

}
