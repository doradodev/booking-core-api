/**
 * 
 */
package com.doradodev.digital.factory.core.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author cristiandorado
 *
 */
@Configuration
@ComponentScan("com.doradodev.digital.factory.core.service")
@PropertySource("classpath:service.properties")
public class ServiceConfig {

}
