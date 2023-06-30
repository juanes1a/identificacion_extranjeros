package co.com.bancolombia.model.clientinformation;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Identification {
    private String type;
    private String number;
}
