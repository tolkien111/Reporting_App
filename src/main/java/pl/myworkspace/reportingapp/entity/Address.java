package pl.myworkspace.reportingapp.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.myworkspace.reportingapp.entity.customer.Customer;

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

    @OneToOne(mappedBy = "address")
    private Customer customer;

    public Address(String street, String streetNumber, String additionalNumber, String city, String zipCode) {
        this.id = UUID.randomUUID();
        this.street = street;
        this.streetNumber = streetNumber;
        this.additionalNumber = additionalNumber;
        this.city = city;
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(streetNumber, address.streetNumber) && Objects.equals(additionalNumber, address.additionalNumber) && Objects.equals(city, address.city) && Objects.equals(zipCode, address.zipCode) && Objects.equals(customer, address.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, streetNumber, additionalNumber, city, zipCode, customer);
    }
}

