package co.com.bancolombia.consumer.expirian.mapper;

import co.com.bancolombia.consumer.expirian.response.*;
import co.com.bancolombia.model.profileinformation.*;
import co.com.bancolombia.model.profileinformation.AgeRange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class ProfileInformationMapper {
    private final DateMapper dateMapper;

    public ProfileInformationMapper() {
        this.dateMapper = new DateMapper();
    }

    public Mono<ProfileInformation> map(Report response) {
        return Mono.just(
                ProfileInformation.builder()
                        .identification(mapIdentification(response.getInformacionBasica()))
                        .custormerDetail(mapCustomerDetail(response.getInformacionBasica()))
                        .supplementaryInformation(mapSupplementaryInformation(response.getInformacionBasica()))
                        .ageRange(mapAgeRange(response.getInformacionBasica().getRangoEdad()))
                        .socialMedia(mapSocialMedia(response.getInformacionBasica()))
                        .reportAddresses(mapReportAddresses(response.getDirecciones()))
                        .reportPhones(mapReportPhones(response.getTelefonos()))
                        .reportEmails(mapReportEmails(response.getEmails()))
                        .build()
        );

    }

    private List<ReportEmails> mapReportEmails(List<Emails> emailsList) {
        List<ReportEmails> reportEmails = new ArrayList<>();
        for (Emails mail : emailsList) {
            reportEmails.add(
                    ReportEmails.builder()
                            .email(mail.getEmail())
                            .numberReports(mail.getNumReportes())
                            .contactProbability(mail.getOrden())
                            .creationDate(dateMapper.map(mail.getReportadoDesde()))
                            .lastDate(dateMapper.map(mail.getUltimoReporte()))
                            .build());

            if (reportEmails.size() == 10) {
                break;
            }
        }
        return reportEmails;
    }

    private List<ReportMobilePhones> mapReportMobilePhones(List<Cellphones> cellphonesList) {
        List<ReportMobilePhones> reportPhonesList = new ArrayList<>();
        for (Cellphones cellphone : cellphonesList) {
            reportPhonesList.add(
                    ReportMobilePhones.builder()
                            .countryCode(cellphone.getCodPais())
                            .mobilePhone(cellphone.getCelular())
                            .numberReports(cellphone.getNumReportes())
                            .contactProbability(cellphone.getOrden())
                            .creationDate(dateMapper.map(cellphone.getReportadoDesde()))
                            .lastDate(dateMapper.map(cellphone.getUltimoReporte()))
                            .build());

            if (reportPhonesList.size() == 10) {
                break;
            }
        }
        return reportPhonesList;
    }

    private List<ReportPhones> mapReportPhones(List<Phones> phonesList) {
        List<ReportPhones> reportPhonesList = new ArrayList<>();
        for (Phones phone : phonesList) {
            reportPhonesList.add(
                    ReportPhones.builder()
                            .countryCode(phone.getCodPais())
                            .departmentName(phone.getNombreDepartamento())
                            .cityName(phone.getNombreCiudad())
                            .daneCode(phone.getCodigoDane())
                            .telephoneNumber(phone.getTelefono())
                            .addressType(phone.getTipo())
                            .numberReports(phone.getNumReportes())
                            .contactProbability(phone.getOrden())
                            .creationDate(dateMapper.map(phone.getReportadoDesde()))
                            .lastDate(dateMapper.map(phone.getUltimoReporte()))
                            .build());

            if (reportPhonesList.size() == 10) {
                break;
            }
        }
        return reportPhonesList;
    }

    private List<ReportAddresses> mapReportAddresses(List<Addresses> addressesList) {
        List<ReportAddresses> reportAddressesList = new ArrayList<>();
        for (Addresses adress : addressesList) {
            reportAddressesList.add(
                    ReportAddresses.builder()
                            .countryCode(adress.getCodPais())
                            .departmentName(adress.getNombreDepartamento())
                            .cityName(adress.getNombreCiudad())
                            .daneCode(adress.getCodigoDane())
                            .area(adress.getZona())
                            .addressType(adress.getTipo())
                            .address(adress.getDato())
                            .locality(adress.getLocalidad())
                            .neighborhood(adress.getBarrio())
                            .numberReports(adress.getNumReportes())
                            .socialStratum(adress.getEstrato())
                            .contactProbability(adress.getOrden())
                            .creationDate(dateMapper.map(adress.getReportadoDesde()))
                            .lastDate(dateMapper.map(adress.getUltimoReporte()))
                            .build());

            if (reportAddressesList.size() == 10) {
                break;
            }
        }
        return reportAddressesList;
    }

    private SocialMedia mapSocialMedia(BasicInformation basicInformation) {
        return SocialMedia.builder()
                .description(basicInformation.getPaginaWeb())
                .build();
    }

    private AgeRange mapAgeRange(co.com.bancolombia.consumer.expirian.response.AgeRange ageRange) {
        return AgeRange.builder()
                .max(ageRange.getMax())
                .min(ageRange.getMin())
                .build();
    }

    private SupplementaryInformation mapSupplementaryInformation(BasicInformation basicInformation) {
        return SupplementaryInformation.builder().documentStatus(
                basicInformation.getEstadoDocumento().equals("98") ? "" :
                        basicInformation.getEstadoDocumento()).build();
    }

    private CustormerDetail mapCustomerDetail(BasicInformation basicInformation) {
        return CustormerDetail.builder()
                .fullNameNaturalPerson(basicInformation.getPrimerNombre() +
                        " " + basicInformation.getSegundoNombre() +
                        " " + basicInformation.getPrimerApellido() +
                        " " + basicInformation.getSegundoNombre())
                .fullNameLegalPerson(basicInformation.getRazonSocial())
                .firstName(basicInformation.getPrimerNombre())
                .secondName(basicInformation.getSegundoNombre())
                .firstSurname(basicInformation.getPrimerApellido())
                .secondSurname(basicInformation.getSegundoNombre())
                .economicActivity(basicInformation.getActividadEconomica())
                .CIIUCode(basicInformation.getCodigoCIIU())
                .gender(basicInformation.getGenero().isBlank() ? "N" : basicInformation.getGenero())
                .build();
    }

    private Identification mapIdentification(BasicInformation basicInformation) {
        return Identification.builder()
                .issuedCity(basicInformation.getMunicipioExpedicion())
                .issuedDepartment(basicInformation.getDepartamentoExpedicion())
                .issuedDate(dateMapper.map(basicInformation.getFechaExpedicion()))
                .build();
    }
}
