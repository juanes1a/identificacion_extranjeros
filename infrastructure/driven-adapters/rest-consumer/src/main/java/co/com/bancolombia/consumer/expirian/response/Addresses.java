package co.com.bancolombia.consumer.expirian.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Addresses {
    private String codPais;
    private String nombreDepartamento;
    private String nombreCiudad;
    private String codigoDane;
    private String zona;
    private String tipo;
    private String dato;
    private String localidad;
    private String barrio;
    private String numReportes;
    private String estrato;
    private String orden;
    private String reportadoDesde;
    private String ultimoReporte;
}
