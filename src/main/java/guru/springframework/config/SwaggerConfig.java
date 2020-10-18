package guru.springframework.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by tairovich_jr on Oct 18, 2020
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig{ // extends WebMvcConfigurationSupport {

	@Bean
	public Docket apis() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.pathMapping("/")
				.apiInfo(metaData())
				.tags(new Tag("vendor", "This is my vendor controller"))
				.tags(new Tag("customer", "This is my customer controller"))
				.tags(new Tag("category", "This is my category controller"));
	}

	private ApiInfo metaData() {
		
		Contact contact = new Contact("TairovichJR", "https://tairovichjr.com", "tairovich.jacob@gmail.com");
		return new ApiInfo("Spring Framework Guru", 
				"Spring Framework 5: Beginner to Guru", 
				"1.0", 
				"Terms of Service: guru", 
				contact, 
				"Apache License Version 2.0", 
				"https://www.apache.org/licenses/LICENSE-2.0",
				new ArrayList<>());
	}
	
	
	
//	@Override
//	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//
//		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//	}

}
