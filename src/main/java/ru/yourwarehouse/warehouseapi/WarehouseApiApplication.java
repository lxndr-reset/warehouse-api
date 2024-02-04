package ru.yourwarehouse.warehouseapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

@SpringBootApplication
@EnableJpaRepositories
public class WarehouseApiApplication {

    /**
     * @param args format: db_username, db_pass, db_connection_URL
     */
    public static void main(String[] args) {
        loadProps(args);
        SpringApplication.run(WarehouseApiApplication.class, args);
    }

    private static void loadProps(String[] args) {

        try (final OutputStream outputStream = new FileOutputStream("application.properties")) {
            Properties props = new Properties();

            props.setProperty("spring.datasource.username", args[0]);
            props.setProperty("spring.datasource.password", args[1]);
            props.setProperty("spring.datasource.url", args[2]);

            props.store(outputStream, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
