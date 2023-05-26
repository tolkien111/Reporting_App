package pl.myworkspace.reportingapp.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "reports")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "report_type")
@DiscriminatorValue(value = "REPORT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Report {

    @Id
    private UUID id;

    @Column(name = "reason")
    @ElementCollection(targetClass = Enum.class)
    @Enumerated(EnumType.STRING)
    private Set<ReasonType> reasonTypeSet;

    @ManyToOne
    @JoinColumn(name = "company_employee_id")
    private CompanyEmployee companyEmployee;

    private LocalDate reportDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "report", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<WorkingTime> workingTimeList;

    private float overallWorkingHours;

    @Column(columnDefinition = "varchar", length = 4000)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "report")
    private List<PartUsed> partUsedList;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "customer_employee_id")
    private CustomerEmployee customerEmployee;


    public Report(@NonNull LocalDate reportDate,
                  float overallWorkingHours,
                  @NonNull String description) {
        this.id = UUID.randomUUID();
        this.reasonTypeSet = new LinkedHashSet<>();
        this.reportDate = reportDate;
        this.workingTimeList = new ArrayList<>();
        this.overallWorkingHours = overallWorkingHours;
        this.description = description;
        this.partUsedList = new ArrayList<>();
    }

    public void addReason(ReasonType reasonType) {
        if (reasonType != null) {
            reasonTypeSet.add(reasonType);
        }
    }

    public void addWorkingTime(WorkingTime workingTime) {
        if (workingTime != null && !workingTimeList.contains(workingTime)) {
            workingTime.setReport(this);
            workingTimeList.add(workingTime);
        }
    }

    public void addPartUsed(PartUsed partUsed) {
        if (partUsed != null && !partUsedList.contains(partUsed)) {
            partUsed.setReport(this);
            partUsedList.add(partUsed);
        }
    }

    public void addDevice(Device device) {
        if (device != null && this.device == null) {
            device.addReport(this);
        }
    }

    public void addCustomer(Customer customer) {
        if (customer != null && this.customer == null) {
            customer.addReport(this);
        }
    }

    public void addCustomerEmployee (CustomerEmployee customerEmployee) {
        if (customerEmployee != null && this.customerEmployee == null) {
            customerEmployee.addReport(this);
        }
    }

    protected void setCompanyEmployee(CompanyEmployee companyEmployee) {
        if (companyEmployee != null && this.companyEmployee == null) {
            this.companyEmployee = companyEmployee;
        }
    }

    protected void setDevice(Device device) {
        if (device != null && this.device == null) {
            this.device = device;
        }
    }

    protected void setCustomer(Customer customer) {
        if (customer != null && this.customer == null) {
            this.customer = customer;
        }
    }

    protected void setCustomerEmployee(CustomerEmployee customerEmployee) {
        if (customerEmployee != null && this.customerEmployee == null) {
            this.customerEmployee = customerEmployee;
        }
    }


}
