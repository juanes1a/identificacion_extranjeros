package co.com.bancolombia.model.clientinformation;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientInformation {
    private Channel channel;
    private Identification identification;
    private CustomerDetail customerDetail;
}
