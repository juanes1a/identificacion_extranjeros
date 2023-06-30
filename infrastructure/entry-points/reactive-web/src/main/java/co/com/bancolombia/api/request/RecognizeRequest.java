package co.com.bancolombia.api.request;

import co.com.bancolombia.model.clientinformation.ClientInformation;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecognizeRequest {
    private ClientInformation data;
}
