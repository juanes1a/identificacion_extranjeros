package co.com.bancolombia.consumer.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
public class ExperianCredentials {

    @Value("${adapter.restconsumer.credentials.username}")
    private String username;

    @Value("${adapter.restconsumer.credentials.password}")
    private String password;

    @Value("${adapter.restconsumer.credentials.client_id}")
    private String clientId;

    @Value("${adapter.restconsumer.credentials.client_secret}")
    private String clientSecret;
}
