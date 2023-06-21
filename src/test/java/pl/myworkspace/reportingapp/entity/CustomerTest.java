package pl.myworkspace.reportingapp.entity;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest extends EntityTest {

    @Test
    void shouldSaveCustomer() {
        //GIVEN
        final var address = new Address("Marszałkowska",
                "21",
                " ",
                "Warszawa",
                "02-100",
                " ");

        final var customer = new Customer("wp@wp.pl",
                "795412568",
                "SevenEleven",
                address);
        //WHEN
        persist(customer);
        //THEN
        final var readCustomer = em.find(Customer.class, customer.getId());
        final var readAddress = readCustomer.getAddress();

        assertEquals(customer, readCustomer);
        assertEquals(address, readAddress);


    }
}