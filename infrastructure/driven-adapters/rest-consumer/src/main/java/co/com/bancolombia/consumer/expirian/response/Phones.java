package co.com.bancolombia.consumer.expirian.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phones {
    private String codPais;
    private String nombreDepartamento;
    private String nombreCiudad;
    private String codigoDane;
    private String telefono;
    private String tipo;
    private String numReportes;
    private String orden;
    private String ultimoReporte;
    private String reportadoDesde;

}
