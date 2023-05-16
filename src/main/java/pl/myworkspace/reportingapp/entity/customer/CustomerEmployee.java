package pl.myworkspace.reportingapp.entity.customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public CustomerEmployee(String email, String phoneNumber, @NotNull String firstName,@NotNull String lastName) {
        super(email, phoneNumber);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setCustomer(Customer customer) {
        if(customer != null && this.customer == null) {
            this.customer = customer;
        }
    }
}
