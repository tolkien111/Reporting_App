package pl.myworkspace.reportingapp.repositiries;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.myworkspace.reportingapp.entity.Address;
import pl.myworkspace.reportingapp.entity.CompanyEmployee;
import pl.myworkspace.reportingapp.entity.CompanyManager;
import pl.myworkspace.reportingapp.entity.Customer;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RepositoriesTest {

    @Autowired
    RepositoryCompanyUser repositoryCompanyUser;

    @Autowired
    RepositoryCustomerUser repositoryCustomerUser;

    @Autowired
    EntityManager entityManager;

    @BeforeEach
    public void init() {
        //CompanyManagers
        final var companyManager01 = new CompanyManager("wp@wp.pl", "aaa", "John", "Smith", "790774564", LocalDate.of(2020, 4, 30));
        final var companyManager02 = new CompanyManager("asd@wp.pl", "bbb", "Amy", "Lockwood", "788668741", LocalDate.of(2018, 6, 14));

        //CompanyEmployees
        final var companyEmployee01 = new CompanyEmployee("dddaaa@gmail.com", "ccc", "Alex", "Last", "4589654245", LocalDate.of(2019, 6, 14));
        final var companyEmployee02 = new CompanyEmployee("dddrrr@gmail.com", "ddd", "James", "Cool", "7896396245", LocalDate.of(2017, 3, 22));
        final var companyEmployee03 = new CompanyEmployee("dddqqq@gmail.com", "fff", "Chase", "English", "99996245", LocalDate.of(2022, 6, 14));

        //Addresses
        final var address01 = new Address("Street", "21", "","London", "38118","");
        final var address02 = new Address("Avenue", "44", "","Manchester", "88552","");

        //Customers
        final var customer01 = new Customer("aasadas@wp.pl","97954654", "Desktop Corp", address01);
        final var customer02 = new Customer("aasadas@gmail.com","997754654", "Omega Corp", address02);


        //SaveIntoDb
        repositoryCompanyUser.saveAllAndFlush(List.of(companyManager01,
                companyManager02,
                companyEmployee01,
                companyEmployee02,
                companyEmployee03));

        repositoryCustomerUser.saveAllAndFlush((List.of(customer01,customer02)));

    }

    @Test
    void shouldFindAllManagers() {
        //GIVEN
        final var companyManager01 = new CompanyManager("wp@wp.pl", "aaa", "John", "Smith", "790774564", LocalDate.of(2020, 4, 30));
        //WHEN
        final var result = repositoryCompanyUser.findAllCompanyManagers();
        //THEN
        Assertions.assertEquals(companyManager01, result.get(0));
        assertEquals(companyManager01, result.get(0));
        assertEquals(2, result.size());
    }

    @Test
    void shouldFindAllEmployees() {
        //GIVEN
        //WHEN
        final var result = repositoryCompanyUser.findAllCompanyEmployees();
        //THEN
        assertEquals(3, result.size());
    }

    @Test
    void shouldFindAllCustomers() {
        //GIVEN
        final var address01 = new Address("Street", "21", "","London", "38118","");
        final var customer01 = new Customer("aasadas@wp.pl","97954654", "Desktop Corp", address01);
        //WHEN
        final var result = repositoryCustomerUser.findAll();
        //THEN
        assertEquals(2, result.size());
        assertEquals(customer01, result.get(0));



    }




}