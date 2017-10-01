package com.doradodev.digital.factory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.doradodev.digital.factory.core.commons.util.AppConfigurator;

/**
 * @author cristiandorado
 *
 */

@SpringBootApplication
public class App implements CommandLineRunner{
	
	
	@Override
	public void run(String... args) throws Exception {	
		System.out.println("book-room - CORE - RESTful API is running...");
	}
	
	public static void main(String[] args) {
        AppConfigurator.configure(args);
        SpringApplication.run(App.class, args);
        
    }

}
