package com.infotech.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.infotech.batch.reader.MyItemReader;
import com.infotech.batch.tasklet.TaskletStep;
import com.infotech.batch.writer.MyItemWriter;
 
 
@Configuration
@EnableBatchProcessing
public class BatchConfig {
 
	@Autowired
	TaskletStep taskletStep;
	
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
 
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
 
     
    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .incrementer(new RunIdIncrementer())
                .start(step1()).next(step2())
                .build();
    }
 
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<String, String> chunk(1)
                .reader(new MyItemReader())
                .writer(new MyItemWriter())
                .build();
    }
    
    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
        		.tasklet(taskletStep)
                .build();
    }
}