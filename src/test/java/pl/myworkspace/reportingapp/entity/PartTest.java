package pl.myworkspace.reportingapp.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartTest extends EntityTest{

    @Test
    void shouldSavePart() {
        //GIVEN
        final var partBase = new PartBase("Main Gear", "1OTA44031", 5);
        final var part = new Part(partBase, "2023122222");
        //WHEN
        persist(part);
        //THEN
        final var readPart = em.find(Part.class, part.getId());
        assertEquals(part, readPart);
    }

}