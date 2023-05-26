package pl.myworkspace.reportingapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Objects;
import java.util.UUID;


@Table(name = "addresses")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public final class Address {

    @Id
    private UUID id;
    private String street;
    private String streetNumber;
    private String additionalNumber;
    private String city;
    private String zipCode;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Address(@NonNull String street,
                   @NonNull String streetNumber,
                   String additionalNumber,
                   @NotNull String city,
                   @NonNull String zipCode) {
        this.id = UUID.randomUUID();
        this.street = street;
        this.streetNumber = streetNumber;
        this.additionalNumber = additionalNumber;
        this.city = city;
        this.zipCode = zipCode;
    }

    void setCustomer(Customer customer) {
        if(customer != null && this.customer == null) {
            this.customer = customer;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(streetNumber, address.streetNumber) && Objects.equals(additionalNumber, address.additionalNumber) && Objects.equals(city, address.city) && Objects.equals(zipCode, address.zipCode) && Objects.equals(customer, address.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, streetNumber, additionalNumber, city, zipCode, customer);
    }
}

