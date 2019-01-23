import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Example {

    private static final String DATE_FORMAT = "yyyy-mm-dd hh:mm:ss z";

    public static void main(String[] args) {

        String dateInString = "2018-04-01T00:00:00+02:00[Europe/Luxembourg]";
        LocalDateTime ldt = LocalDateTime.parse(dateInString, DateTimeFormatter.ISO_ZONED_DATE_TIME);

        ZoneId singaporeZoneId = ZoneId.of("Europe/Luxembourg");
        System.out.println("TimeZone : " + singaporeZoneId);

        //LocalDateTime + ZoneId = ZonedDateTime
        ZonedDateTime asiaZonedDateTime = ldt.atZone(singaporeZoneId);
        System.out.println("Date (Singapore) : " + asiaZonedDateTime);

        ZoneId newYokZoneId = ZoneId.of("America/New_York");
        System.out.println("TimeZone : " + newYokZoneId);

        ZonedDateTime nyDateTime = asiaZonedDateTime.withZoneSameInstant(newYokZoneId);
        System.out.println("Date (New York) : " + nyDateTime);

        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
        System.out.println("\n---DateTimeFormatter---");
        System.out.println("Date (Singapore) : " + format.format(asiaZonedDateTime));
        System.out.println("Date (New York) : " + format.format(nyDateTime));

    }

}
