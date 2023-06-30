package co.com.bancolombia.consumer.expirian.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cellphones {
    private String codPais;
    private String celular;
    private String numReportes;
    private String orden;
    private String reportadoDesde;
    private String ultimoReporte;

}
