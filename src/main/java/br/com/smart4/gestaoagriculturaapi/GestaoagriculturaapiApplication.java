package br.com.smart4.gestaoagriculturaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class GestaoagriculturaapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoagriculturaapiApplication.class, args);
	}

}
