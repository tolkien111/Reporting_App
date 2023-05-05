package pl.myworkspace.reportingapp.entity.report;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.myworkspace.reportingapp.entity.customer.Customer;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CreatedReport extends Report{

    private String installedDevice; //TODO change to InstalledDevice installedDevice
    @ManyToOne
    private Customer customer;
}
