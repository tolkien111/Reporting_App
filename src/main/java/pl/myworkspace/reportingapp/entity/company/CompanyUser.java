package pl.myworkspace.reportingapp.entity.company;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "company_users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter

public abstract class CompanyUser {

    @Id
    private UUID id;
    private String email;
    private String phoneNumber;

    @Column(name = "user_type", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)

    private CompanyUserType userType;
    private boolean activeUser;

    protected CompanyUser(@NotNull String email,@NotNull String phoneNumber) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.activeUser = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyUser that = (CompanyUser) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, phoneNumber);
    }
}


