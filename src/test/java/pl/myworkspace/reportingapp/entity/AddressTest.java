package pl.myworkspace.reportingapp.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest extends EntityTest {

    @Test
    void shouldSaveAddresses(){
        //GIVEN
        final var address = new Address("Marszałkowska",
                "21",
                " ",
                "Warszawa",
                "02-100",
                " ");
        //WHEN
        persist(address);
        //THEN
        final var readAddress = em.find(Address.class, address.getId());
        assertEquals(address, readAddress);
    }

}