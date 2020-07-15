package com.yanpp.tunte.sample.easyrule.fizz;

import org.jeasy.rules.annotation.*;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/20
 **/
@Rule
public class FizzRule {

    @Condition
    public boolean isFizz(@Fact("number") Integer number){
        return number%5 == 0;
    }

    @Action
    public void printFizz(){
        System.out.println("fizz");
    }

    @Priority
    public int getPriority(){
        return 1;
    }

}
