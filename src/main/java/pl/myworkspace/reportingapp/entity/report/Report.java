package pl.myworkspace.reportingapp.entity.report;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.myworkspace.reportingapp.entity.company.CompanyEmployee;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "report_type")
@NoArgsConstructor
@Getter
public abstract class Report {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "company_employee_id")
    private CompanyEmployee companyEmployee;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "report_id")
    private List<WorkingTime> workingTimeList;

    private float overallWorkingHours;

    @Column(columnDefinition = "TEXT")
    private String description;

    //private List <UsedPart> usedPartList;

    public Report(@NotNull float overallWorkingHours,@NotNull String description, String lifOfUsedParts) {
        this.id = UUID.randomUUID();
        this.workingTimeList = new ArrayList<>();
        this.overallWorkingHours = overallWorkingHours;
        this.description = description;
        //this.usedPartList = new ArrayList<>();
    }
}
