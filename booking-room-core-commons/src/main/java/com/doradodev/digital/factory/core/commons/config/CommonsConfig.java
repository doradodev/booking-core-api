/**
 * 
 */
package com.doradodev.digital.factory.core.commons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

/**
 * @author cristiandorado
 *
 */
@Configuration
@ComponentScan("com.doradodev.digital.factory.core.commons")
public class CommonsConfig {
	
public static final String OBJECT_MAPPER = "objectMapper";
    
    @Primary
    @Bean(name = OBJECT_MAPPER)
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
            .configure(WRITE_DATES_AS_TIMESTAMPS, true)
            .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(FAIL_ON_EMPTY_BEANS, false)
            .setSerializationInclusion(NON_NULL);
    }

    @Bean
    @Primary
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocalOverride(true);
        configurer.setIgnoreResourceNotFound(false);
        configurer.setIgnoreUnresolvablePlaceholders(true);

        return configurer;
    }

}
