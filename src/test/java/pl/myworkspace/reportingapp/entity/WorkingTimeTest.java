package pl.myworkspace.reportingapp.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class WorkingTimeTest extends EntityTest {

    @Test
    void shouldSaveWorkingTime() {
        //GIVEN
        final var workingTime1 = new WorkingTime(LocalDate.of(2023, 4, 22), LocalTime.of(7, 0), LocalTime.of(16, 0));
        final var workingTime2 = new WorkingTime(LocalDate.of(2023, 4, 23), LocalTime.of(8, 0), LocalTime.of(15, 0));
        //WHEN
        persist(workingTime1);
        persist(workingTime2);
        //THEN
        final var readWorkingTime1 = em.find(WorkingTime.class, workingTime1.getId());
        final var readWorkingTime2 = em.find(WorkingTime.class, workingTime2.getId());
        assertEquals(workingTime1, readWorkingTime1);
        assertEquals(workingTime2, readWorkingTime2);
        System.out.println(workingTime1);
        System.out.println(readWorkingTime1);
        System.out.println();
        System.out.println(workingTime2);
        System.out.println(readWorkingTime2);
    }

}