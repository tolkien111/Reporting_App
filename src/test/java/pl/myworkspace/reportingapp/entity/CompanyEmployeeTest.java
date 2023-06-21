package pl.myworkspace.reportingapp.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CompanyEmployeeTest extends EntityTest {

    @Test
    void shouldSaveCompanyEmployee(){
        //GIVEN
        final var companyEmployee = new CompanyEmployee("wp@wp.pl",
                "aaa",
                "John",
                "Smith",
                "790774564",
                LocalDate.now());
        //WHEN
        persist(companyEmployee);
        //THEN
        final var readCompanyEmployee = em.find(CompanyEmployee.class, companyEmployee.getId());
        assertEquals(companyEmployee, readCompanyEmployee);

    }


}