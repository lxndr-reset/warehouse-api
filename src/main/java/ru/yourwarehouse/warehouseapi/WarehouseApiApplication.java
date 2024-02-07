package ru.yourwarehouse.warehouseapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Stream;

@SpringBootApplication
@EnableJpaRepositories
public class WarehouseApiApplication {

    /**
     * @param args format: --db.url= --db.username= --db.pass= (any order)
     */
    public static void main(String[] args) {
        SpringApplication.run(WarehouseApiApplication.class, args);
    }
}
