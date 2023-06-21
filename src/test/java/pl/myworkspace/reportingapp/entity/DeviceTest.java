package pl.myworkspace.reportingapp.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceTest extends EntityTest{

    @Test
    void shouldSaveDevice() {
        //GIVEN
        final var deviceBase = new DeviceBase("Fanuc Robot","1OTA44332", 3);
        final var device = new Device(deviceBase,"202307110432");
        //WHEN
        persist(device);
        //THEN
        final var readDevice = em.find(Device.class, device.getId());
        assertEquals(device, readDevice);
    }
}