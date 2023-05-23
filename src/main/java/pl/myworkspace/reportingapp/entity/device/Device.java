package pl.myworkspace.reportingapp.entity.device;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.myworkspace.reportingapp.entity.customer.Customer;
import pl.myworkspace.reportingapp.entity.report.Report;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "devices")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@Getter
public class Device {

    @Id
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deviceBase_id")
    private DeviceBase deviceBase;

    private String serialNumber;

    @ManyToOne
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "device", fetch = FetchType.LAZY)
    private List<Report> reportList;


    public void addReport(Report report){
        if(report !=null && !reportList.contains(report)){
            report.setDevice(this);
            reportList.add(report);
        }
    }

    public void setCustomer(Customer customer) {
        if (customer != null && this.customer == null) {
            this.customer = customer;
        }
    }

}
