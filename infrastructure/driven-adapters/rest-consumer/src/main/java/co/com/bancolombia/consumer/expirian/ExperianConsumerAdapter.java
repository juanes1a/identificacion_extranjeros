package co.com.bancolombia.consumer.expirian;

import co.com.bancolombia.consumer.config.ExperianCredentials;
import co.com.bancolombia.consumer.expirian.constants.RecognizeConstants;
import co.com.bancolombia.consumer.expirian.constants.TokenConstants;
import co.com.bancolombia.consumer.expirian.mapper.DocumentTypeMapper;
import co.com.bancolombia.consumer.expirian.mapper.ExperianValidation;
import co.com.bancolombia.consumer.expirian.mapper.ProfileInformationMapper;
import co.com.bancolombia.consumer.expirian.response.RecognizeResponse;
import co.com.bancolombia.consumer.expirian.response.TokenResponse;
import co.com.bancolombia.model.clientinformation.ClientInformation;
import co.com.bancolombia.model.clientinformation.gateways.ForeignersIdentificationService;
import co.com.bancolombia.model.profileinformation.ProfileInformation;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Log4j2
@Repository
public class ExperianConsumerAdapter implements ForeignersIdentificationService {

    private static final Logger LOGGER = LogManager.getLogger(ExperianConsumerAdapter.class);

    private final WebClient tokenClient;
    private final WebClient recognizeClient;
    private final Cache<String, TokenResponse> cache;
    private final ExperianCredentials experianCredentials;

    private final ExperianValidation experianValidation;
    private final ProfileInformationMapper profileInformationMapper;

    public ExperianConsumerAdapter(@Qualifier("getWebClientToken") WebClient tokenClient,
                                   @Qualifier("getWebClientRecognize") WebClient recognizeClient,
                                   @Qualifier("getExperianCredentials") ExperianCredentials experianCredentials,
                                   Cache<String, TokenResponse> cache) {
        this.tokenClient = tokenClient;
        this.recognizeClient = recognizeClient;
        this.cache = cache;
        this.experianCredentials = experianCredentials;
        this.experianValidation = new ExperianValidation();
        this.profileInformationMapper = new ProfileInformationMapper();
    }

    @Override
    public Mono<ProfileInformation> getIdentificationForForeigners(ClientInformation clientInformation) {
        return getTokenFromCache()
                .doOnNext(System.out::println)
                .flatMap(token -> this.callRecognizeApi(clientInformation, token.getAccess_token()))
                .flatMap(response -> profileInformationMapper.map(response.getReporte()));
    }

    private Mono<RecognizeResponse> callRecognizeApi(ClientInformation clientInformation, String token) {
        return recognizeClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(RecognizeConstants.RECOGNIZE_PATH)
                        .queryParam(RecognizeConstants.RECOGNIZE_ID_TYPE_NAME,
                                RecognizeConstants.RECOGNIZE_ID_TYPE_VALUE)
                        .queryParam(RecognizeConstants.RECOGNIZE_ID_NUMBER_NAME,
                                RecognizeConstants.RECOGNIZE_ID_NUMBER_VALUE)
                        .queryParam(RecognizeConstants.RECOGNIZE_NIT_NAME,
                                RecognizeConstants.RECOGNIZE_NIT_VALUE)
                        .queryParam(RecognizeConstants.DOCUMENT_TYPE_NAME,
                                DocumentTypeMapper.map(clientInformation.getIdentification().getType()))
                        .queryParam(RecognizeConstants.DOCUMENT_NUMBER_NAME,
                                clientInformation.getIdentification().getNumber())
                        .queryParam(RecognizeConstants.SURNAME_NAME,
                                clientInformation.getCustomerDetail().getFirstSurname())
                        .queryParam(RecognizeConstants.VALIDATE_NAME_NAME,
                                experianValidation.shouldValidateName(clientInformation.getIdentification().getType(),
                                        clientInformation.getChannel().getCode()))
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .header("access_token", token)
                .retrieve()
                .bodyToMono(RecognizeResponse.class)
                .onErrorMap(Exception.class, ex -> {
                    log.error("Error after consuming service CUSTOMER: {}", ex.toString());
                    return new RuntimeException(ex);
                });
    }

    private Mono<TokenResponse> getTokenFromCache() {
        return cache.getIfPresent(TokenConstants.CACHE_KEY) != null ?
                Mono.just(cache.getIfPresent(TokenConstants.CACHE_KEY)) :
                createToken().flatMap(this::setCaheToken);
    }

    private Mono<TokenResponse> setCaheToken(TokenResponse value) {
        return Mono.fromSupplier(() -> {
                    cache.put(TokenConstants.CACHE_KEY, value);
                    return value;
                }
        ).thenReturn(value);
    }

    private Mono<TokenResponse> createToken() {
        return tokenClient
                .post()
                .uri(TokenConstants.TOKEN_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(TokenConstants.GRANT_TYPE_PARAM_NAME, TokenConstants.GRANT_TYPE_PARAM_VALUE)
                        .with(TokenConstants.SCOPE_PARAM_NAME, TokenConstants.SCOPE_PARAM_VALUE)
                        .with(TokenConstants.USERNAME_PARAM_NAME, experianCredentials.getUsername())
                        .with(TokenConstants.PASSWORD_PARAM_NAME, experianCredentials.getPassword())
                        .with(TokenConstants.CLIENT_ID_PARAM_NAME, experianCredentials.getClientId())
                        .with(TokenConstants.CLIENT_SECRET_PARAM_NAME, experianCredentials.getClientSecret()))
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .onErrorMap(RuntimeException.class, ex -> {
                            log.error("Error after consuming service TOKEN: {}", ex.toString());
                            return new RuntimeException("Error obteniendo el token");
                        }
                );
    }
}


//NO SE VALIDA EL APELLIDO SI ES PERSONA JURIDICA O EL CONSUMIDOR ES (ALM o BLM)
//