package co.com.bancolombia.usecase;

import co.com.bancolombia.model.clientinformation.ClientInformation;
import co.com.bancolombia.model.clientinformation.gateways.ForeignersIdentificationService;
import co.com.bancolombia.model.profileinformation.ProfileInformation;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ProfileInformatioUseCase {
    private final ForeignersIdentificationService foreignersIdentificationService;

    public Mono<ProfileInformation> recognizeClientInformation(ClientInformation clientInformation) {
        return foreignersIdentificationService.getIdentificationForForeigners(clientInformation);
    }
}
