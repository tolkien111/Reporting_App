package pl.myworkspace.reportingapp.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "devices")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Device {

    @Id
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_base_id")
    private DeviceBase deviceBase;

    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "device", fetch = FetchType.LAZY)
    private List<Report> reportList;


    public Device(@NonNull DeviceBase deviceBase,
                  @NonNull String serialNumber) {
        this.id = UUID.randomUUID();
        this.deviceBase = deviceBase;
        this.serialNumber = serialNumber;
        this.reportList = new ArrayList<>();
    }

    protected void addReport(Report report) {
        if (report != null && !reportList.contains(report)) {
            report.setDevice(this);
            reportList.add(report);
        }
    }


    protected void setCustomer(Customer customer) {
        if (customer != null && this.customer == null) {
            this.customer = customer;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(deviceBase, device.deviceBase) && Objects.equals(serialNumber, device.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceBase, serialNumber);
    }
}
