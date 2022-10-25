package io.github.yudady.date;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateLongTeat {
    public static void main(String[] args) {
        Date d = new Date();
        System.out.println("d.getTime() = " + d.getTime());
        LocalDateTime now = LocalDateTime.now();
        long timeInMillis = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        System.out.println("timeInMillis = " + timeInMillis);
    }
}
