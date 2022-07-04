package br.com.verly.bovespafii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class BovespaFiiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BovespaFiiApplication.class, args);
	}

}
