/**
 * 
 */
package com.doradodev.digital.factory.core.api.config;

import static java.util.Map.Entry;
import static org.springframework.hateoas.MediaTypes.HAL_JSON;
import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.HAL;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author cristiandorado
 *
 */
@Configuration
@EnableWebMvc
@EnableHypermediaSupport(type=HAL)
public class WebConfig extends WebMvcConfigurerAdapter{
	
	private static final Map<String, String> RESOURCE_HANDLERS;

	static {
		RESOURCE_HANDLERS = new LinkedHashMap<>(1);
		RESOURCE_HANDLERS.put("/**", "classpath:/public/");
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/docs/");
		viewResolver.setSuffix(".html");

		return viewResolver;
	}

	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(HAL_JSON);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String pattern;
		for (Entry<String, String> handler : RESOURCE_HANDLERS.entrySet()) {
			pattern = handler.getKey();
			if (!registry.hasMappingForPattern(pattern)) {
				registry.addResourceHandler(pattern)
						.addResourceLocations(handler.getValue());
			}
		}
	}

}
