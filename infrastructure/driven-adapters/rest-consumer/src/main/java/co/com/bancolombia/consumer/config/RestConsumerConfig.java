package co.com.bancolombia.consumer.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

import static io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Configuration
public class RestConsumerConfig {

    @Value("${adapter.restconsumer.base_url_token}")
    private String baseUrlToken;

    @Value("${adapter.restconsumer.base_url_recognize}")
    private String baseUrlRecognize;

    @Value("${adapter.restconsumer.timeout}")
    private int timeout;

    @Bean
    public ExperianCredentials getExperianCredentials() {
        return new ExperianCredentials();
    }

    @Bean
    public Cache caffeineConfig() {
        return Caffeine.newBuilder().expireAfterWrite(55, TimeUnit.MINUTES).build();
    }

    @Bean
    public WebClient getWebClientRecognize() {
        return WebClient.builder()
                .baseUrl(baseUrlRecognize)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .clientConnector(getClientHttpConnector())
                .build();
    }

    @Bean
    public WebClient getWebClientToken() {
        return WebClient.builder()
                .baseUrl(baseUrlToken)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .clientConnector(getClientHttpConnector())
                .build();
    }

    private ClientHttpConnector getClientHttpConnector() {
        return new ReactorClientHttpConnector(HttpClient.create()
                .compress(true)
                .keepAlive(true)
                .option(CONNECT_TIMEOUT_MILLIS, timeout)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(timeout, MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(timeout, MILLISECONDS));
                }));
    }

}
