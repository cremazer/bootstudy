package com.example.bootstudy;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BootstudyApplication {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job taskletJob; // 올바른 Job 빈을 주입받도록 합니다.

	public static void main(String[] args) {
		SpringApplication.run(BootstudyApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			jobLauncher.run(taskletJob, new JobParameters());
		};
	}
}
