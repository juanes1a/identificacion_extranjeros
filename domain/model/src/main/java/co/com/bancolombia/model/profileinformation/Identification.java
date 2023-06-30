package co.com.bancolombia.model.profileinformation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Identification {
    private final String issuedDate;
    private final String issuedDepartment;
    private final String issuedCity;
}
