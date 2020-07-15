package com.yanpp.tunte.sample.easyrule.anno;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/20
 **/
public class Launcher {

    public static void main(String[] args) {

        //创建facts
        Facts facts = new Facts();

        //创建规则
        Rules rules = new Rules();
        rules.register(new HelloWorldRule());

        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules,facts);

    }

}
