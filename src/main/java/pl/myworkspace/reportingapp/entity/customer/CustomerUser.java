package pl.myworkspace.reportingapp.entity.customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter

public abstract class CustomerUser {

    @Id
    private UUID id;
    private String email;
    private String phoneNumber;

    public CustomerUser(@NotNull String email,@NotNull String phoneNumber) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.phoneNumber = phoneNumber;
            }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerUser that = (CustomerUser) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, phoneNumber);
    }
}
