package co.com.bancolombia.consumer.expirian.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
    private String access_token;
    private int expires_in;
    private String token_type;
    private String scope;

    @Builder.Default
    private Date now = new Date();

}
