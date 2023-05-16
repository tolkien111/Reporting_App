package pl.myworkspace.reportingapp.entity.customer;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.myworkspace.reportingapp.entity.Address;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter

public class Customer extends CustomerUser {


    private String name;

    @OneToOne(mappedBy = "customer", orphanRemoval = true)
    private Address address;


    private String listOfCreatedDevices; //TODO change to @OneToMany (mappedBy = "customer", cascade = CascadeType.ALL)
    //TODO <CreatedReport> listOfInstalledDevices;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.LAZY)
    private List<CustomerEmployee> customerEmployeesList;


    public void addAddress(Address address) { // dodać tą metodę w konstruktorze 12.05.2023!!
        if(address != null && this.address == null) {
            address.setCustomer(this);
            this.address = address;
        }
    }

    public void addCustomerEmployee (CustomerEmployee customerEmployee){
        if(customerEmployee != null && customerEmployeesList.contains(customerEmployee)){
            customerEmployee.setCustomer(this);
            customerEmployeesList.add(customerEmployee);
        }
    }
}
