package pl.myworkspace.reportingapp.entity.report;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.myworkspace.reportingapp.entity.company.CompanyEmployee;
import pl.myworkspace.reportingapp.entity.part.UsedPart;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "report_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class Report {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "company_employee_id")
    private CompanyEmployee companyEmployee;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<WorkingTime> workingTimeList;

    private float overallWorkingHours;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "report")
    private List<UsedPart> usedPartList;

    public Report(CompanyEmployee companyEmployee, float overallWorkingHours, String description) {
        this.id = UUID.randomUUID();
        this.companyEmployee = companyEmployee;
        this.workingTimeList = new ArrayList<>();
        this.overallWorkingHours = overallWorkingHours;
        this.description = description;
        this.usedPartList = new ArrayList<>();
    }

    public void addWorkingTime(WorkingTime workingTime){
        if(workingTime != null && workingTimeList.contains(workingTime)){
            workingTime.setReport(this);
            workingTimeList.add(workingTime);

        }
    }

    public void setCompanyEmployee(CompanyEmployee companyEmployee) {
        if(companyEmployee != null && this.companyEmployee == null)
        this.companyEmployee = companyEmployee;
    }
}
