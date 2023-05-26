package pl.myworkspace.reportingapp.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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
    private boolean activeUser;

    public CustomerUser(@NonNull String email,
                        @NonNull String phoneNumber) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.activeUser = false;
    }

    public void setActiveUser(boolean activeUser) {
        this.activeUser = activeUser;
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
