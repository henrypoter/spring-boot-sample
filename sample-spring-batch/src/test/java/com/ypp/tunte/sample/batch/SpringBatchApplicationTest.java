package com.ypp.tunte.sample.batch;


import com.ypp.tunte.sample.batch.config.BatchConfig;
import com.ypp.tunte.sample.batch.config.HelloWorldJobConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/4
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {SpringBatchApplicationTest.BatchTestConfig.class})
public class SpringBatchApplicationTest {
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void testHelloWorldJob() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        assertThat(jobExecution.getExitStatus().getExitCode())
                .isEqualTo("COMPLETED");
    }

    @Configuration
    @Import({BatchConfig.class, HelloWorldJobConfig.class})
    static class BatchTestConfig {

        @Autowired
        private Job helloWorlJob;

        @Bean
        JobLauncherTestUtils jobLauncherTestUtils()
                throws NoSuchJobException {
            JobLauncherTestUtils jobLauncherTestUtils =  new JobLauncherTestUtils();
            jobLauncherTestUtils.setJob(helloWorlJob);
            return jobLauncherTestUtils;
        }
    }
}