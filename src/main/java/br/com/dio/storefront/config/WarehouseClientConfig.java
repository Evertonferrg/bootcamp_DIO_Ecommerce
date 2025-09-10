package br.com.dio.storefront.config;

<<<<<<< HEAD

import org.springframework.beans.factory.annotation.Value;
=======
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
>>>>>>> dd08b0b (Commit inicial do projeto storefront)
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class WarehouseClientConfig {

<<<<<<< HEAD
=======
    @Bean
>>>>>>> dd08b0b (Commit inicial do projeto storefront)
    RestClient storefrontClient(@Value("${warehouse.base-path}") final String basePath){
        return RestClient.create(basePath);

    }
}
