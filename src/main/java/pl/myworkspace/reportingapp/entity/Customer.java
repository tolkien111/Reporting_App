package pl.myworkspace.reportingapp.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customers")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // spróbuj z PRIVATE
@Getter

public class Customer extends CustomerUser {


    private String name;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customer", orphanRemoval = true)
    @JoinColumn(name = "address_id",unique = true)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.LAZY)
    private List<CustomerEmployee> customerEmployeesList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Device> deviceList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.LAZY)
    private List<ReportBase> reportBaseList;

    public Customer(@NonNull String email,
                    @NonNull String phoneNumber,
                    @NonNull String name,
                    @NonNull Address address) {
        super(email, phoneNumber);
        this.name = name;
        this.address = addAddress(address);
        this.customerEmployeesList = new ArrayList<>();
        this.deviceList = new ArrayList<>();
        this.reportBaseList = new ArrayList<>();
    }

    private Address addAddress(Address address) {
        if (this.address == null) {
            address.setCustomer(this);
            return address;
        }
        return this.address;
    }

    protected void addReportBase(ReportBase reportBase) {
        if (reportBase != null && !reportBaseList.contains(reportBase)) {
            reportBase.setCustomer(this);
            reportBaseList.add(reportBase);
        }
    }

    public void addDevice(Device device){
        if (device != null && !deviceList.contains(device)) {
            device.setCustomer(this);
            deviceList.add(device);
        }
    }

    public void addCustomerEmployee(CustomerEmployee customerEmployee) {
        if (customerEmployee != null && !customerEmployeesList.contains(customerEmployee)) {
            customerEmployee.setCustomer(this);
            customerEmployeesList.add(customerEmployee);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, address);
    }
}
