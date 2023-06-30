package co.com.bancolombia.model.profileinformation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ReportPhones {
    private final String countryCode;
    private final String departmentName;
    private final String cityName;
    private final String daneCode;
    private final String telephoneNumber;
    private final String addressType;
    private final String numberReports;
    private final String contactProbability;
    private final String creationDate;
    private final String lastDate;
}
