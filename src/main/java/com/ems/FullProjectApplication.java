package com.ems;

import com.ems.rrbillupload.FileStoragePojo;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStoragePojo.class
})
@OpenAPIDefinition(info = @Info(title = "employee",version = "1.0",description = "employee"))
public class FullProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FullProjectApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
