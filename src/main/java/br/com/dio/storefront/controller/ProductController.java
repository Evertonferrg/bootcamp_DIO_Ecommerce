package br.com.dio.storefront.controller;

import lombok.AllArgsConstructor;
<<<<<<< HEAD
import lombok.extern.java.Log;
=======

>>>>>>> dd08b0b (Commit inicial do projeto storefront)
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
@AllArgsConstructor
@Log4j2
public class ProductController {

    @GetMapping("test")
    void test(){
        log.info("test");
<<<<<<< HEAD

=======
>>>>>>> dd08b0b (Commit inicial do projeto storefront)
    }
}
