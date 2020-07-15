package com.yanpp.tunte.sample.easyrule.fizz;

import org.jeasy.rules.annotation.*;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/20
 **/
@Rule
public class BuzzRule {

    @Condition
    public boolean isBuzz(@Fact("number") Integer number){
        return number%7 ==0;
    }

    @Action
    public void printBuzz(){
        System.out.println("buzz");
    }

    @Priority
    public int getPriority(){
        return 2;
    }
}
