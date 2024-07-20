package com.example.bootstudy;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Bean
	public Job taskletJob() {
		return new JobBuilder("tasklet_job", jobRepository)
			.incrementer(new RunIdIncrementer())
			.start(taskletStep1())
			.build();
	}

	@Bean
	public Step taskletStep1() {
		return new StepBuilder("tasklet_step1", jobRepository)
			.tasklet(tasklet(), transactionManager)
			.build();
	}

	@Bean
	public Tasklet tasklet() {
		return (contribution, chunkContext) -> {
			System.out.println("Hello, World!");
			return RepeatStatus.FINISHED;
		};
	}
}
