package co.com.bancolombia.model.profileinformation;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class ProfileInformation {
    private final Identification identification;
    private final CustormerDetail custormerDetail;
    private final SupplementaryInformation supplementaryInformation;
    private final AgeRange ageRange;
    private final SocialMedia socialMedia;
    private final String rut;
    private final List<HistoricalCIIU> historicalCIIU;
    private final List<ReportAddresses> reportAddresses;
    private final List<ReportPhones> reportPhones;
    private final List<ReportMobilePhones> reportMobilePhones;
    private final List<ReportEmails> reportEmails;
}
