package pl.myworkspace.reportingapp.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartUsedTest extends EntityTest {

    @Test
    void shouldSavePartUsed() {

        //GIVEN
        final var partUsed = new PartUsed();
        //WHEN
        persist(partUsed);
        //THEN
        final var readPartUsed = em.find(PartUsed.class, partUsed.getId());
        assertEquals(partUsed, readPartUsed);



    }
}