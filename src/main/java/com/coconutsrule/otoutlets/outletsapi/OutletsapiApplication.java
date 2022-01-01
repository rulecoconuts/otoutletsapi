package com.coconutsrule.otoutlets.outletsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableJpaAuditing(auditorAwareRef = "auditorAwareRef", dateTimeProviderRef = "dateTimeProviderRef")
@EnableWebSecurity
@SpringBootApplication
public class OutletsapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutletsapiApplication.class, args);
	}

}
