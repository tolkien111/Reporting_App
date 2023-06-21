package pl.myworkspace.reportingapp.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CompanyManagerTest extends EntityTest {

    @Test
    void shouldSaveCompanyEmployee(){
        //GIVEN
        final var companyManager = new CompanyManager("yahoo@wp.pl",
                "bbb",
                "Alan",
                "James",
                "669876222",
                LocalDate.now());
        //WHEN
        persist(companyManager);
        //THEN
        final var readCompanyManager = em.find(CompanyManager.class, companyManager.getId());
        assertEquals(companyManager, readCompanyManager);

    }

}