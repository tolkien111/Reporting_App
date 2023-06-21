package pl.myworkspace.reportingapp.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceBaseTest extends EntityTest {

    @Test
    void shouldSaveDeviceBase() {
        //GIVEN
        final var deviceBase = new DeviceBase("Fanuc Robot","1OTA44332", 3);
        //WHEN
        persist(deviceBase);
        //THEN
        final var readDeviceBase = em.find(DeviceBase.class, deviceBase.getId());
        assertEquals(deviceBase, readDeviceBase);
    }
}