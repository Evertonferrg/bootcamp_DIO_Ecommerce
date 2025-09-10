package br.com.dio.storefront.config;

<<<<<<< HEAD

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
=======
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
>>>>>>> dd08b0b (Commit inicial do projeto storefront)
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API da Vitrine do E-commerce")
                        .version("1.0")
                        .description("Documentação da API da vitrine do e-commerce."));
    }
}
