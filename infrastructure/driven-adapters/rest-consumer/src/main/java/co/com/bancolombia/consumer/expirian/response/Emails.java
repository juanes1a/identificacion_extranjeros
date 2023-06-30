package co.com.bancolombia.consumer.expirian.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emails {
    private String email;
    private String numReportes;
    private String orden;
    private String reportadoDesde;
    private String ultimoReporte;

}
