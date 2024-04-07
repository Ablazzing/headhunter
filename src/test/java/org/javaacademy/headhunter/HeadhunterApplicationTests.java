package org.javaacademy.headhunter;

import org.javaacademy.headhunter.resume.Resume;
import org.javaacademy.headhunter.resume.User;
import org.javaacademy.headhunter.resume.UserRepository;
import org.javaacademy.headhunter.service.HeadHunterService;
import org.javaacademy.headhunter.vacancy.Company;
import org.javaacademy.headhunter.vacancy.CompanyRepository;
import org.javaacademy.headhunter.vacancy.Vacancy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static java.math.BigDecimal.TEN;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class HeadhunterApplicationTests {
    private static final String SKILLS_DESCRIPTION = "умею все";
	private static final String COMPANY_NAME = "yandex";
    @Autowired
    private HeadHunterService headHunterService;
    @Autowired
    private UserRepository userRepository;
	@Autowired
	private CompanyRepository companyRepository;

    @Test
    void createUser() {
        headHunterService.createUser("aaa@yandex.ru", "testName");
        Collection<User> users = userRepository.findAll();
        assertEquals(1, users.size());
        User user = new ArrayList<>(users).get(0);
        assertEquals("aaa@yandex.ru", user.getEmail());
        assertEquals("testName", user.getName());
    }

    @Test
    void createResume() {
        String email = "aaa@yandex.ru";
        headHunterService.createUser(email, "testName");
        headHunterService.createResume(email, TEN, "умею все");
        List<Resume> resumes = headHunterService.findAllResume();
        assertEquals(1, resumes.size());
        Resume resume = resumes.get(0);
        assertEquals(TEN, resume.getSalary());
        assertEquals("умею все", resume.getSkillsDescription());
    }

    @Test
    void findAllResume() {
        createUserAndResume("test1@email.ru");
        createUserAndResume("test2@email.ru");
        List<Resume> resumes = headHunterService.findAllResume();
        assertEquals(2, resumes.size());
        long resumeCount = resumes.stream()
                .filter(resume -> TEN.equals(resume.getSalary())
                                  && SKILLS_DESCRIPTION.equals(resume.getSkillsDescription())).count();
		assertEquals(2, resumeCount);
    }

	@Test
	void createCompany() {
		headHunterService.createCompany(COMPANY_NAME);
		Company company = companyRepository.findByName(COMPANY_NAME).orElseThrow();
		assertEquals(COMPANY_NAME, company.getName());
	}

	@Test
	void createVacancy() {
		headHunterService.createCompany(COMPANY_NAME);
		headHunterService.createVacancy(COMPANY_NAME, "testTitle", "testDescr", TEN);
		Company company = companyRepository.findByName(COMPANY_NAME).orElseThrow();
		assertEquals(1, company.getVacancySet().size());
		Vacancy vacancy = new ArrayList<>(company.getVacancySet()).get(0);
		assertEquals("testTitle", vacancy.getTitle());
		assertEquals("testDescr", vacancy.getDescription());
		assertEquals(TEN, vacancy.getSalary());
		assertEquals("RUB", vacancy.getCurrencyName());
	}

	@Test
	void findVacanciesByCompany() {
		headHunterService.createCompany(COMPANY_NAME);
		headHunterService.createVacancy(COMPANY_NAME, "testTitle", "testDescr", TEN);
		Set<Vacancy> vacanciesByCompany = headHunterService.findVacanciesByCompany(COMPANY_NAME);
		assertEquals(1, vacanciesByCompany.size());
		Vacancy vacancy = new ArrayList<>(vacanciesByCompany).get(0);
		assertEquals("testTitle", vacancy.getTitle());
		assertEquals("testDescr", vacancy.getDescription());
		assertEquals(TEN, vacancy.getSalary());
		assertEquals("RUB", vacancy.getCurrencyName());
	}

    private void createUserAndResume(String email) {
        headHunterService.createUser(email, "testName");
        headHunterService.createResume(email, TEN, "умею все");
    }

}
