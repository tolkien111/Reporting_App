package pl.myworkspace.reportingapp.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import pl.myworkspace.reportingapp.entity.enums.ReasonType;

import java.time.Duration;
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

    @Column(name = "reason", columnDefinition = "varchar(40)")
    @ElementCollection(targetClass = Enum.class)
    @Enumerated(value = EnumType.STRING)
    private Set<ReasonType> reasonTypeSet;

    @ManyToOne
    @JoinColumn(name = "company_employee_id")
    private CompanyEmployee companyEmployee;

    private LocalDate reportDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "report", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<WorkingTime> workingTimeList;

    private double overallWorkingHours;

    @Column(length = 4000)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "report", fetch = FetchType.LAZY)
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
                  @NonNull String description) {
        this.id = UUID.randomUUID();
        this.reasonTypeSet = new LinkedHashSet<>();
        this.reportDate = reportDate;
        this.workingTimeList = new LinkedHashSet<>();
        this.overallWorkingHours = 0;
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
            overallWorkingHours = sumTime(workingTimeList);
        }
    }

    public static double sumTime (Set<WorkingTime> timeSet){
        return timeSet.stream()
                .mapToDouble(workingTime1 -> Duration.between(workingTime1.getEndTime(), workingTime1.getStartTime()).toSeconds())
                .sum();
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

    public void addCustomerEmployee(CustomerEmployee customerEmployee) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Double.compare(report.overallWorkingHours, overallWorkingHours) == 0 && Objects.equals(companyEmployee, report.companyEmployee) && Objects.equals(reportDate, report.reportDate) && Objects.equals(description, report.description) && Objects.equals(device, report.device) && Objects.equals(customer, report.customer) && Objects.equals(customerEmployee, report.customerEmployee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyEmployee, reportDate, overallWorkingHours, description, device, customer, customerEmployee);
    }
}
