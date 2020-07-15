package com.yanpp.tunte.sample.easyrule.anno;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
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
public class WeatherRules {
    @Rule(name = "weather rule1", description = "simple rule1", priority = 2)
    public static class WeatherRule1 {

        @Condition
        public boolean when(@Fact("fact") WeatherRulesFact fact) {
            return fact.rain;
        }

        @Action
        public void then() {
            System.out.println("It rains, with rule1 .");
        }
    }

    @Rule(name = "weather rule2", description = "simple rule2", priority = 1)
    public static class WeatherRule2 {

        @Condition
        public boolean when(@Fact("fact") WeatherRulesFact fact) {
            return fact.rain;
        }

        @Action
        public void then() {
            System.out.println("It rains, with rule2 .");

        }


    }

    public static class WeatherRulesFact{
        private boolean rain;

        private String result;

        public boolean isRain() {
            return rain;
        }

        public void setRain(boolean rain) {
            this.rain = rain;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }

    public static void main(String[] args) throws Exception{
        Rules rules = new Rules();



        rules.register(new WeatherRule1());
        rules.register(new WeatherRule2());

        Facts facts = new Facts();
        WeatherRulesFact fact=new WeatherRulesFact();
        fact.setRain(true);

        facts.put("fact", fact);

        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
    }

}
