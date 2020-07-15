package com.ypp.tunte.sample.drools.demo1;

import com.ypp.tunte.sample.drools.demo1.model.Car;
import com.ypp.tunte.sample.drools.demo1.model.Person;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/20
 **/
public class Demo extends Base{

    @Test
    public void test(){

//        KieServices kieServices = KieServices.Factory.get();
//        //默认自动加载 META-INF/kmodule.xml
//        KieContainer kieContainer = kieServices.getKieClasspathContainer();
//
//        //kmodule.xml 中定义的 ksession name
//        KieSession kieSession = kieContainer.newKieSession("all-rules");

        KieSession kieSession = getSession();

        Person p1 = new Person();
        p1.setAge(20);

        Car car = new Car();
        car.setPerson(p1);

        kieSession.insert(car);

        int count = kieSession.fireAllRules();

        System.out.println(count);
        System.out.println(car.getDiscount());

        kieSession.dispose();
    }


    @Test
    public void test1(){

        KieSession kieSession = getSession("test-group");
        Person p1 = new Person();
        p1.setAge(20);
        Car car = new Car();
        car.setPerson(p1);

        kieSession.insert(car);

        int count = kieSession.fireAllRules();

        System.out.println(count);
        System.out.println(car.getDiscount());

        kieSession.dispose();
    }

}
