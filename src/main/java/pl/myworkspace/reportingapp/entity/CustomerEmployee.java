package pl.myworkspace.reportingapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CustomerEmployee that = (CustomerEmployee) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName);
    }
}
