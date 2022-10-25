package io.github.yudady.date;

import java.time.LocalDateTime;

public class DateIsAfterTeat {
    public static void main(String[] args) {


        LocalDateTime now = LocalDateTime.now();

        LocalDateTime notExpired = now.plusMinutes(10);

        boolean after = notExpired.isAfter(LocalDateTime.now());
        System.out.println("after = " + after);

    }
}
