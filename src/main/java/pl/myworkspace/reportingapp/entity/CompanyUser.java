package pl.myworkspace.reportingapp.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import pl.myworkspace.reportingapp.entity.enums.CompanyUserType;

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
    private String userPassword;
    private boolean activeUser;

    @Column(name = "user_type", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private CompanyUserType userType;

    protected CompanyUser(@NonNull String email,
                          @NonNull String userPassword) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.userPassword = userPassword;
        this.activeUser = false;
    }

    public void setActiveUser(boolean activeUser) {
        this.activeUser = activeUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyUser that = (CompanyUser) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}


