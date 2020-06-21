package br.com.poc.monitoracao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@ComponentScan("br.com.poc.monitoracao")
@EnableIntegration
@IntegrationComponentScan
public class PocMonitoracaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocMonitoracaoApplication.class, args);
	}

}
