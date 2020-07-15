package com.ypp.tunte.sample.batch.config;

import com.ypp.tunte.sample.batch.component.PersonItemProcessor;
import com.ypp.tunte.sample.batch.model.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/4
 **/
@Configuration
public class HelloWorldJobConfig {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    // 注入StepBuilderFactory，用来构建Step
    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloWorlJob() {
        return jobBuilderFactory.get("helloWorldJob")
                .start(helloWorldStep(stepBuilderFactory)).build();
    }

    @Bean
    public Step helloWorldStep(StepBuilderFactory stepBuilders) {
        return stepBuilders.get("helloWorldStep")
                .<Person, String>chunk(2).reader(reader())
                .processor(processor()).writer(writer()).build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Person> reader() {
        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .resource(new ClassPathResource("csv/persons.csv"))
                .delimited().names(new String[] {"firstName", "lastName"})
                .targetType(Person.class).build();
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    @Bean
    public FlatFileItemWriter<String> writer() {
        return new FlatFileItemWriterBuilder<String>()
                .name("greetingItemWriter")
                .resource(new FileSystemResource(
                        "target/test-outputs/greetings.txt"))
                .lineAggregator(new PassThroughLineAggregator<>()).build();
    }



}
