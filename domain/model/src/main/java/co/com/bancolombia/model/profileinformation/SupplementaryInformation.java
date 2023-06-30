package co.com.bancolombia.model.profileinformation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class SupplementaryInformation {
    private final String documentStatus;
}
