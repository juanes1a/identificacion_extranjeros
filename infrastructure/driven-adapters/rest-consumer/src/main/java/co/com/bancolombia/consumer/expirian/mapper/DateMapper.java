package co.com.bancolombia.consumer.expirian.mapper;


public class DateMapper {
    public String map(String stringDate){
        String format = "%s-%s-%s";
        String[] dateSplit = stringDate.split("/");
        return String.format(format, dateSplit[2], dateSplit[1], dateSplit[0]);

    }
}
