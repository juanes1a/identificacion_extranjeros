package co.com.bancolombia.consumer.expirian.mapper;

public class DocumentTypeMapper {
    public static String map(String documentType) {
        switch (documentType) {
            case "TIPDOC_FS000":
                return "9";
            case "TIPDOC_FS001":
                return "1";
            case "TIPDOC_FS002":
                return "4";
            case "TIPDOC_FS003":
                return "3";
            case "TIPDOC_FS004":
                return "7";
            case "TIPDOC_FS005":
                return "5";
            case "TIPDOC_FS006":
                return "8";
            default:
                throw new RuntimeException("");
        }
    }
}
