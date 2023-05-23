package pl.myworkspace.reportingapp.entity.report;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.myworkspace.reportingapp.entity.company.CompanyEmployee;
import pl.myworkspace.reportingapp.entity.customer.Customer;
import pl.myworkspace.reportingapp.entity.customer.CustomerEmployee;
import pl.myworkspace.reportingapp.entity.device.Device;
import pl.myworkspace.reportingapp.entity.part.UsedPart;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "reports")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "report_type")
//@DiscriminatorValue(value = "REPORT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Report {

    @Id
    private UUID id;

    @Column(name = "reason")
    @Enumerated(EnumType.STRING)
    private Set<ReasonType> reasonTypeList;

    @ManyToOne
    @JoinColumn(name = "companyEmployee_id")
    private CompanyEmployee companyEmployee;

    private LocalDate reportDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "report", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<WorkingTime> workingTimeList;

    private float overallWorkingHours;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "report")
    private List<UsedPart> usedPartList;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "customerEmployee_id")
    private CustomerEmployee customerEmployee;


    public Report(CompanyEmployee companyEmployee,
                  LocalDate reportDate,
                  float overallWorkingHours,
                  String description,
                  Device device,
                  Customer customer,
                  CustomerEmployee customerEmployee) {
        this.id = UUID.randomUUID();
        this.reasonTypeList = new HashSet<>();
        this.companyEmployee = companyEmployee;
        this.reportDate = reportDate;
        this.workingTimeList = new ArrayList<>();
        this.overallWorkingHours = overallWorkingHours;
        this.description = description;
        this.usedPartList = new ArrayList<>();
        this.device = device;
        this.customer = customer;
        this.customerEmployee = customerEmployee;
    }

    public void addWorkingTime(WorkingTime workingTime) {
        if (workingTime != null && !workingTimeList.contains(workingTime)) {
            workingTime.setReport(this);
            workingTimeList.add(workingTime);
        }
    }

    public void addReason(ReasonType reasonType) {
        if (reasonType != null) {
            reasonTypeList.add(reasonType);
        }
    }

    public void setCompanyEmployee(CompanyEmployee companyEmployee) {
        if (companyEmployee != null && this.companyEmployee == null) {
            this.companyEmployee = companyEmployee;
        }
    }

    public void setDevice(Device device) {
        if (device != null && this.device == null) {
            this.device = device;
        }
    }

    public void setCustomer(Customer customer) {
        if (customer != null && this.customer == null) {
            this.customer = customer;
        }
    }

    public void setCustomerEmployee(CustomerEmployee customerEmployee) {
        if (customerEmployee != null && this.customerEmployee == null) {
            this.customerEmployee = customerEmployee;
        }
    }
}
