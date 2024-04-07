package org.javaacademy.headhunter.resume;

import lombok.Data;
import lombok.NonNull;

@Data
public class User {
    @NonNull
    private String name;
    @NonNull
    private String email;
    private Resume resume;
}
