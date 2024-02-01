package com.sergioramos.models;

import lombok.*;

import java.util.Date;

@Getter @Setter @ToString @AllArgsConstructor @RequiredArgsConstructor
public class User {
    private Integer id;
    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private String email;
    @NonNull private String password;
    @NonNull private Date birth;
    @NonNull private Character sex;
}
