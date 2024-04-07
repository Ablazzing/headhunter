package org.javaacademy.headhunter.vacancy;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class CompanyRepository {
    private final Map<String, Company> companyMap = new HashMap<>();

    public void save(Company company) {
        if (companyMap.containsKey(company.getName())) {
            throw new RuntimeException("Company already exists");
        }
        companyMap.put(company.getName(), company);
    }

    public Optional<Company> findByName(String name) {
        return Optional.ofNullable(companyMap.get(name));
    }
}
