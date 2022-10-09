package files;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Payload {

    public static String addActivity(Integer id, String tittle){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime today = LocalDateTime.now();


        return "{\n" +
                "  \"id\": "+id+",\n" +
                "  \"title\": \""+tittle+"\",\n" +
                "  \"dueDate\": \""+dtf.format(today)+"\",\n" +
                "  \"completed\": true\n" +
                "}";
    }
}
