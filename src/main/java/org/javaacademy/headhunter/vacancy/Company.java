package org.javaacademy.headhunter.vacancy;

import lombok.Data;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

@Data
public class Company {
    @NonNull
    private String name;
    private Set<Vacancy> vacancySet = new HashSet<>();
}
