package io.github.yudady.date;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class ZonedDateTimeTest {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTimeInLocal = localDateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime zonedDateTimeInUTC = zonedDateTimeInLocal.withZoneSameInstant(ZoneOffset.UTC);

        ZonedDateTime zonedDateTimeInAmerica = zonedDateTimeInLocal.withZoneSameInstant(ZoneId.of("America/New_York"));
        ZonedDateTime zonedDateTimeInAsia = zonedDateTimeInLocal.withZoneSameInstant(ZoneId.of("Asia/Shanghai"));
        System.out.println("localDateTime           : " + localDateTime.toString());
        System.out.println("zonedDateTimeInLocal    : " + zonedDateTimeInLocal.toString());
        System.out.println("===========================================");
        System.out.println("zonedDateTimeInUTC      : " + zonedDateTimeInUTC.toString());
        System.out.println("zonedDateTimeInAmerica  : " + zonedDateTimeInAmerica.toString());
        System.out.println("zonedDateTimeInAsia     : " + zonedDateTimeInAsia.toString());
    }

}
