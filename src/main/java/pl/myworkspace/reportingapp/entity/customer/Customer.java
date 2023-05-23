package pl.myworkspace.reportingapp.entity.customer;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.myworkspace.reportingapp.entity.Address;
import pl.myworkspace.reportingapp.entity.device.Device;
import pl.myworkspace.reportingapp.entity.report.Report;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // spróbuj z PRIVATE
@Getter

public class Customer extends CustomerUser {


    private String name;

    @OneToOne(mappedBy = "customer", orphanRemoval = true)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Report> reportList;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Device> deviceList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.LAZY)
    private List<CustomerEmployee> customerEmployeesList;


    public Customer(String email,
                    String phoneNumber,
                    String name,
                    Address address) {
        super(email, phoneNumber);
        this.name = name;
        this.address = addAddressConstructor(address);
        this.reportList = new ArrayList<>();
        this.deviceList = new ArrayList<>();
        this.customerEmployeesList = new ArrayList<>();
    }

    private Address addAddressConstructor(Address address) {
        if (address != null && this.address == null) {
            address.setCustomer(this);
            return address;
        }
        return this.address;
    }

    public void addReport(Report report) {
        if (report != null && !reportList.contains(report)) {
            report.setCustomer(this);
            reportList.add(report);
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
}
