package com.modelpharmacy;

import com.modelpharmacy.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class ModelPharmacyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModelPharmacyApplication.class, args);
    }

}
