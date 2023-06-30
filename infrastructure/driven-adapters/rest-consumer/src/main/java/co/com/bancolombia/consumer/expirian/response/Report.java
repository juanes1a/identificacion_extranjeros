package co.com.bancolombia.consumer.expirian.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    private String tipoIdBuscado;
    private String respuesta;
    private String fechaConsulta;
    private BasicInformation informacionBasica;
    private List<Addresses> direcciones;
    private List<Phones> telefonos;
    private List<Cellphones> celulares;
    private List<Emails> emails;
}
