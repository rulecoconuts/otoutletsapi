package com.coconutsrule.otoutlets.outletsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "auditorAwareRef", dateTimeProviderRef = "dateTimeProviderRef")
@SpringBootApplication
public class OutletsapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutletsapiApplication.class, args);
	}

}
