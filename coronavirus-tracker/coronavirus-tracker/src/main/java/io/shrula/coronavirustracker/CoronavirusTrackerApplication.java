package io.shrula.coronavirustracker;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class CoronavirusTrackerApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		ApplicationContext applicationContext=SpringApplication.run(CoronavirusTrackerApplication.class, args);
		CoronaVirusData service=applicationContext.getBean(CoronaVirusData.class);
		service.fetchVirusData();
	}

}
