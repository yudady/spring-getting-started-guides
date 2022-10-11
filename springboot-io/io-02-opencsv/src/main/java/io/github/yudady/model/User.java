package io.github.yudady.model;

import com.opencsv.bean.CsvBindByPosition;

public class User {

    @CsvBindByPosition(position = 0)
    public String id;

    @CsvBindByPosition(position = 1)
    public String firstName;

    @CsvBindByPosition(position = 2)
    public String lastName;

    @Override
    public String toString() {
        return "User{" +
            "id='" + id + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
    }
}