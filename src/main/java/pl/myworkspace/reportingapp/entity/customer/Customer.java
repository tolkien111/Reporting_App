package pl.myworkspace.reportingapp.entity.customer;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.myworkspace.reportingapp.entity.Address;

import java.util.ArrayList;

@Entity
@Table(name = "customers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter

public class Customer extends  CustomerUser{


    private String name;


    private String listOfInstalledDevices; //TODO change to @OneToMany (mappedBy = "customer", cascade = CascadeType.ALL)
                                                            //TODO <CreatedReport> listOfInstalledDevices;



    @OneToMany (cascade = CascadeType.ALL, mappedBy = "customer")
    private ArrayList<CustomerEmployee> customerEmployeesList;

    public Customer(String email, String phoneNumber, @NotNull String name) {
        super(email, phoneNumber);
        this.name = name;
        this.customerEmployeesList = new ArrayList<>();
    }
}
