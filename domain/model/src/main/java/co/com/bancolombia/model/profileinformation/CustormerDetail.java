package co.com.bancolombia.model.profileinformation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class CustormerDetail {
    private final String fullNameNaturalPerson;
    private final String fullNameLegalPerson;
    private final String firstName;
    private final String secondName;
    private final String firstSurname;
    private final String secondSurname;
    private final String economicActivity;
    private final String CIIUCode;
    private final String gender;
}
