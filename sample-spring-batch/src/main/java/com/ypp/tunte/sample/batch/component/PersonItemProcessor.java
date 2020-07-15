package com.ypp.tunte.sample.batch.component;

import com.ypp.tunte.sample.batch.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/4
 **/
public class PersonItemProcessor implements ItemProcessor<Person, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonItemProcessor.class);
    @Override
    public String process(Person person) throws Exception {
        String greeting = "Hello " + person.getFirstName() + " "
                + person.getLastName() + "!";

        LOGGER.info("converting '{}' into '{}'", person, greeting);
        return greeting;
    }
}
