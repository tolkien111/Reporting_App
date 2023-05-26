package pl.myworkspace.reportingapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer_employees")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter

public class CustomerEmployee extends CustomerUser{

    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerEmployee", fetch = FetchType.LAZY)
    private List<Report> reportList;

    public CustomerEmployee(@NonNull String email,
                            @NotNull String phoneNumber,
                            @NonNull String firstName,
                            @NotNull String lastName) {
        super(email, phoneNumber);
        this.firstName = firstName;
        this.lastName = lastName;
        this.reportList = new ArrayList<>();
    }


    protected void addReport(Report report) {
        if (report != null && !reportList.contains(report)) {
            report.setCustomerEmployee(this);
            reportList.add(report);
        }
    }

    protected void setCustomer(Customer customer) {
        if(customer != null && this.customer == null) {
            this.customer = customer;
        }
    }


}
