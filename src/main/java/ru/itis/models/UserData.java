package ru.itis.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserData {
    private String name;
    private String passport;
    private Integer age;
    private String passportDate;
}
