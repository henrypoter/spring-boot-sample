package com.ypp.tunte.sample.drools.demo1;

import org.apache.commons.lang3.StringUtils;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/20
 **/
public class Base {
    public KieSession getSession() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //kmodule.xml 中定义的 ksession name
        KieSession kieSession = kieContainer.newKieSession("all-rules");
        return kieSession;
    }

    public KieSession getSession(String agendaGroupName) {
        KieSession session = this.getSession();
        if (StringUtils.isNoneBlank(agendaGroupName)) {
            session.getAgenda().getAgendaGroup(agendaGroupName).setFocus();
        }

        return session;
    }
}
