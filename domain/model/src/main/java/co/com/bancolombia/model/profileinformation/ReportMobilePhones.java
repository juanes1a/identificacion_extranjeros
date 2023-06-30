package co.com.bancolombia.model.profileinformation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ReportMobilePhones {
    private final String countryCode;
    private final String mobilePhone;
    private final String numberReports;
    private final String contactProbability;
    private final String creationDate;
    private final String lastDate;
}
