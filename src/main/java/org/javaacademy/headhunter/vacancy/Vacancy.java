package org.javaacademy.headhunter.vacancy;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class Vacancy {
    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private BigDecimal salary;
    @NonNull
    private String currencyName;
}
