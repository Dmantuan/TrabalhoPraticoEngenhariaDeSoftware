package ufes.services.localDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatadorLocalDate {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate formatar(String data){
        return LocalDate.parse(data, formatter);
    }
}
