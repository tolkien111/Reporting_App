package pl.myworkspace.reportingapp.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerEmployeeTest extends EntityTest {

    @Test
    void shouldSaveCustomerEmployee(){
        //GIVEN
        final var customerEmployee = new CustomerEmployee("wp.pl", "799005544", "Anna", "James");
        //WHEN
        persist(customerEmployee);
        //THEN
        final var readCustomerEmployee = em.find(CustomerEmployee.class, customerEmployee.getId());
        assertEquals(customerEmployee,readCustomerEmployee);
    }

}