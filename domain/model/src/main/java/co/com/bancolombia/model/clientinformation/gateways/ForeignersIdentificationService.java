package co.com.bancolombia.model.clientinformation.gateways;

import co.com.bancolombia.model.clientinformation.ClientInformation;
import co.com.bancolombia.model.profileinformation.ProfileInformation;
import reactor.core.publisher.Mono;

public interface ForeignersIdentificationService {
    Mono<ProfileInformation> getIdentificationForForeigners(ClientInformation clientInformation);
}
