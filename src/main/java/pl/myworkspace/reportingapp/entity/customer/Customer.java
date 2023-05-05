package pl.myworkspace.reportingapp.entity.customer;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.myworkspace.reportingapp.entity.Address;
import pl.myworkspace.reportingapp.entity.company.CompanyManager;
import pl.myworkspace.reportingapp.entity.report.CreatedReport;

import java.util.ArrayList;

@Entity
@Table(name = "customers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter

public class Customer extends  CustomerUser{


    private String name;

    @OneToMany (mappedBy = "customer", cascade = CascadeType.ALL)
    private ArrayList <CreatedReport> listOfInstalledDevices;

    @OneToOne
    private Address address;

    @OneToMany (mappedBy = "customer", cascade = CascadeType.ALL)
    private ArrayList<CustomerEmployee> customerEmployeesList;

    public Customer(String email, String phoneNumber, @NotNull String name) {
        super(email, phoneNumber);
        this.name = name;
        this.listOfInstalledDevices = new ArrayList<>();
        this.customerEmployeesList = new ArrayList<>();
    }
}
