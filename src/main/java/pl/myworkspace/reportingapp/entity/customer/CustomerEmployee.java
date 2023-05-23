package pl.myworkspace.reportingapp.entity.customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.myworkspace.reportingapp.entity.report.Report;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer_employees")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter

public class CustomerEmployee extends CustomerUser{

    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerEmployee", fetch = FetchType.LAZY)
    private List<Report> reportList;

    public CustomerEmployee(String email, String phoneNumber, String firstName, String lastName, Customer customer, List<Report> reportList) {
        super(email, phoneNumber);
        this.firstName = firstName;
        this.lastName = lastName;
        this.customer = customer;
        this.reportList = new ArrayList<>();
    }

    public void addReport(Report report) {
        if (report != null && !reportList.contains(report)) {
            report.setCustomerEmployee(this);
            reportList.add(report);
        }
    }

    public void setCustomer(Customer customer) {
        if(customer != null && this.customer == null) {
            this.customer = customer;
        }
    }


}
