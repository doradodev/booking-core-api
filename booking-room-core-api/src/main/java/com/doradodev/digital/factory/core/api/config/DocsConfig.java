/**
 * 
 */
package com.doradodev.digital.factory.core.api.config;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author cristiandorado
 *
 */
@Configuration
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
public class DocsConfig {
	
	private static final String TITLE = "Book - Room - CORE - RESTful API";
	private static final String DESCRIPTION = "For Room CORE purpose.<br/> It represents model of Room, etc!";
	private static final String VERSION = "1.0.0";

	@Bean
	public ApiInfo apiInfo() {
		return new ApiInfo(TITLE, DESCRIPTION, VERSION, null, null, null, null);
	}

	@Bean
	public Docket customImplementation() {
		return new Docket(SWAGGER_2).apiInfo(apiInfo());
	}

}
