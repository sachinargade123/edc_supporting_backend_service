package org.eclipse.tractusx.eback;

import org.eclipse.tractusx.eback.service.StorageProperties;
import org.eclipse.tractusx.eback.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class EDCBackendSupportingApplication {

	public static void main(String[] args) {
	 SpringApplication.run(EDCBackendSupportingApplication.class, args);
	}

	
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
