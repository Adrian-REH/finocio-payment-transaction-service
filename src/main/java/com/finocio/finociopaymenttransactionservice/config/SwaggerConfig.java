package com.finocio.finociopaymenttransactionservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * http://localhost:8080/swagger-ui/ --> HTML
 */
@Configuration // anotacion para todas las clases spring de configuracion
@EnableSwagger2
public class SwaggerConfig {


    @Bean // permite que spring invoque este método para obtener un objeto que inyectar donde lo necesite
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiDetails() {

        return new ApiInfo("Finocio-payment-transaction-service",
                " Desarrollar un servicio web que permita la creación y consulta de transacciones de pago.\n",
                "1.0",
                "http://localhost/terms",
                new Contact("Adrian Herrera", "Prueba", "adrianherrera.r.e@gmail.com"),
                "APACHE LICENSE, VERSION 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList());
    }

}