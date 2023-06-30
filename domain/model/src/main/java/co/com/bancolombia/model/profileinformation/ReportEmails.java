package co.com.bancolombia.model.profileinformation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ReportEmails {
    private final String email;
    private final String numberReports;
    private final String contactProbability;
    private final String creationDate;
    private final String lastDate;
}
