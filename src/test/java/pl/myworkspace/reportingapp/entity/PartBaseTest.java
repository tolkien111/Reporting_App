package pl.myworkspace.reportingapp.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartBaseTest extends EntityTest {
    @Test
    void shouldSavePartBase() {
        //GIVEN
        final var partBase = new PartBase("Main Gear", "1OTA44031", 5);
        //WHEN
        persist(partBase);
        //THEN
        final var readPartBase = em.find(PartBase.class, partBase.getId());
        assertEquals(partBase, readPartBase);
    }
}