package co.com.bancolombia.consumer.expirian.mapper;

public class ExperianValidation {
    public boolean shouldValidateName(String documentType, String channelCode) {
        if (channelCode.equals("ALM") || channelCode.equals("BLM") || documentType.equals("TIPDOC_FS001")) {
            return false;
        } else {
            return true;
        }
    }
}
