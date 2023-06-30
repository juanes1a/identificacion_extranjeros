package co.com.bancolombia.consumer.expirian.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicInformation {
    private String actividadEconomica;
    private String codigoCIIU;
    private String estadoDocumento;
    private String fechaExpedicion;
    private String genero;
    private String municipioExpedicion;
    private String departamentoExpedicion;
    private String nombreCompleto;
    private String primerApellido;
    private String segundoApellido;
    private String primerNombre;
    private String segundoNombre;
    private String numeroIdentificacion;
    private String paginaWeb;
    private String razonSocial;
    private String tipoIdentificacion;
    private String rut;
    private AgeRange rangoEdad;
}
