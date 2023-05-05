package pl.myworkspace.reportingapp.entity.company;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@DiscriminatorValue("COMPANY_MANAGER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter

public class CompanyManager extends CompanyUser{

    private String firstName;
    private String lastName;
    private String userPassword;
    private LocalDate startDateOfWork;

    public CompanyManager(String email,
                          String phoneNumber,
                          @NotNull String firstName,
                          @NotNull String lastName,
                          @NotNull String userPassword,
                          @NotNull LocalDate startDateOfWork) {
        super(email, phoneNumber);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userPassword = userPassword;
        this.startDateOfWork = startDateOfWork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CompanyManager that = (CompanyManager) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(startDateOfWork, that.startDateOfWork);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, startDateOfWork);
    }
}
