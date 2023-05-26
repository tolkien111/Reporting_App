package pl.myworkspace.reportingapp.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@DiscriminatorValue("COMPANY_MANAGER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter

public class CompanyManager extends CompanyUser {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate startDateOfWork;

    public CompanyManager(@NonNull String email,
                          @NonNull String userPassword,
                          @NonNull String firstName,
                          @NonNull String lastName,
                          @NonNull String phoneNumber,
                          @NonNull LocalDate startDateOfWork) {
        super(email, userPassword);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.startDateOfWork = startDateOfWork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CompanyManager that = (CompanyManager) o;
        return Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(phoneNumber, that.phoneNumber)
                && Objects.equals(startDateOfWork, that.startDateOfWork);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, phoneNumber, startDateOfWork);
    }
}
