package com.kh.rupp_dev.boukryuniversity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BoukryUniversityApplication {
	public static void main(String[] args) {
		SpringApplication.run(BoukryUniversityApplication.class, args);
	}
}
