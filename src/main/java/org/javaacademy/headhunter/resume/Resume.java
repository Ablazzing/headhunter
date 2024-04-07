package org.javaacademy.headhunter.resume;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class Resume {
    //(зарплата на которую претендует пользователь, описание того что умеет)
    @NonNull
    private BigDecimal salary;
    @NonNull
    private String skillsDescription;
}
