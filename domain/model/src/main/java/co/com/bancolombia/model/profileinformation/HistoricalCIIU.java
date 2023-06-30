package co.com.bancolombia.model.profileinformation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class HistoricalCIIU {
    private final String CIIUCode;
    private final String economicActivity;
    private final String date;
}
