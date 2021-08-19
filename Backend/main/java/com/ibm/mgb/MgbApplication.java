package com.ibm.mgb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.ibm.mgb.*")
@ComponentScan(basePackages = { "com.ibm.mgb.*" , "de.westlb.mgb.*" })
@EntityScan(basePackages = {"com.ibm.mgb.*", "de.westlb.mgb.*" })   
public class MgbApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
        SpringApplication.run(MgbApplication.class, args);
    }
}
