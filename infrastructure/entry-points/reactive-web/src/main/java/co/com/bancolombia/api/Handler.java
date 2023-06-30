package co.com.bancolombia.api;

import co.com.bancolombia.api.request.RecognizeRequest;
import co.com.bancolombia.usecase.ProfileInformatioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final ProfileInformatioUseCase profileInformatioUseCase;
    public Mono<ServerResponse> recognizeMasterController(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(RecognizeRequest.class)
                .flatMap(request -> profileInformatioUseCase.recognizeClientInformation(request.getData()))
                .flatMap(ServerResponse.ok()::bodyValue);
    }


}
