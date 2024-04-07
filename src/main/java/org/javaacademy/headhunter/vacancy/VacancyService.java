package org.javaacademy.headhunter.vacancy;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class VacancyService {
    @Value("${vacancy.currency}")
    private String currencyName;
    private final CompanyRepository companyRepository;

    public void createCompany(String companyName) {
        Company company = new Company(companyName);
        companyRepository.save(company);
    }

    public void createVacancy(String companyName,
                              String vacancyTitle,
                              String vacancyDescr,
                              BigDecimal salary) {
        Company company = getCompany(companyName);
        Vacancy vacancy = new Vacancy(vacancyTitle, vacancyDescr, salary, currencyName);
        company.getVacancySet().add(vacancy);
    }

    public Set<Vacancy> findVacanciesByCompanyName(String companyName) {
        Company company = getCompany(companyName);
        return company.getVacancySet();
    }

    private Company getCompany(String companyName) {
        return companyRepository.findByName(companyName)
                .orElseThrow(() -> new RuntimeException("Company not exists"));
    }
}
