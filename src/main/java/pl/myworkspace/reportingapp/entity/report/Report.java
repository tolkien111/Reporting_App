package pl.myworkspace.reportingapp.entity.report;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import pl.myworkspace.reportingapp.entity.customer.CustomerEmployee;

import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name = "reports")
@NoArgsConstructor
@Getter
public abstract class Report {

    @Id
    private UUID id;
    @ManyToOne
    private CustomerEmployee customerEmployee;
    @Transient
    private ArrayList <WorkingTime> listOfWorkingTime;
    private float overallWorkingHours;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String lifOfUsedParts; //TODO change to -> ArrayList <UsedPart>

    public Report(@NotNull CustomerEmployee customerEmployee,@NotNull float overallWorkingHours,@NotNull String description, String lifOfUsedParts) {
        this.id = UUID.randomUUID();
        this.customerEmployee = customerEmployee;
        this.listOfWorkingTime = new ArrayList<>();
        this.overallWorkingHours = overallWorkingHours;
        this.description = description;
        this.lifOfUsedParts = lifOfUsedParts;
    }
}
