package org.javaacademy.headhunter.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.headhunter.resume.Resume;
import org.javaacademy.headhunter.resume.ResumeService;
import org.javaacademy.headhunter.vacancy.Vacancy;
import org.javaacademy.headhunter.vacancy.VacancyService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HeadHunterService {
    private final ResumeService resumeService;
    private final VacancyService vacancyService;

    public void createCompany(String companyName) {
        vacancyService.createCompany(companyName);
    }

    public void createVacancy(String companyName,
                              String vacancyTitle,
                              String vacancyDescr,
                              BigDecimal salary) {
        vacancyService.createVacancy(companyName, vacancyTitle, vacancyDescr, salary);
    }

    public Set<Vacancy> findVacanciesByCompany(String companyName) {
        return vacancyService.findVacanciesByCompanyName(companyName);
    }

    public void createUser(String email, String name) {
        resumeService.createUser(email, name);
    }

    public void createResume(String email, BigDecimal salary, String skillsDescription) {
        resumeService.createResume(email, salary, skillsDescription);
    }

    public List<Resume> findAllResume() {
        return resumeService.findAllResume();
    }
}
