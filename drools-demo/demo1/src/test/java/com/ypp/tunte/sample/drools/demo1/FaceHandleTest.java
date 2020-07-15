package com.ypp.tunte.sample.drools.demo1;

import com.ypp.tunte.sample.drools.demo1.model.Person;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/20
 **/
public class FaceHandleTest extends Base {

    @Test
    public void test(){
        KieSession kieSession = getSession("faceHandleTest");
        Person p=new Person();
        p.setAge(30);

        FactHandle factHandle = kieSession.insert(p);

        int count = kieSession.fireAllRules();

        System.out.println(p.getName());

        System.out.println(factHandle.toExternalForm());

        p.setAge(10);

        kieSession.getAgenda().getAgendaGroup("faceHandleTest").setFocus();
        kieSession.update(factHandle,p);
        kieSession.fireAllRules();

        Person p2= (Person) kieSession.getObject(factHandle);
        System.out.println(p2.getName());

    }

}
