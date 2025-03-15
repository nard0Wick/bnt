package com.leo.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import java.sql.Time;
import java.util.TimeZone;

@EnableJpaAuditing
@EnableAsync
@SpringBootApplication
public class BookNetworkApiApplication {
	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Mexico_City"));
		SpringApplication.run(BookNetworkApiApplication.class, args);
	}

}
