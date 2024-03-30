package com.unimag.Tienda;
import  org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
public class TestContainersConfig {
    PostgreSQLContainer<?>postgreSQLContainer(){
        return new PostgreSQLContainer<>("postgres:15-alpine");
    }
}
