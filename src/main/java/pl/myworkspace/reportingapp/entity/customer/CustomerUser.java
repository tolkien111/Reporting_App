package pl.myworkspace.reportingapp.entity.customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "customer_users")
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
}
