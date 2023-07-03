package pl.myworkspace.reportingapp.entity;


import jakarta.persistence.*;
import lombok.Getter;

import java.util.*;

@Entity
@Table(name = "parts_used")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
public class PartUsed {

    @Id
    private UUID id;

    private int amount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partUsed")
    private Set<Part> partSet;

    @ManyToOne
    @JoinColumn(name = "reportBase_id")
    private ReportBase reportBase;


    public PartUsed() {
        this.id = UUID.randomUUID();
        this.amount = 0;
        this.partSet = new LinkedHashSet<>();
    }

    public void addPart(Part part) {
        if (part != null) {
            part.setPartUsed(this);
            partSet.add(part);
            amount = partSet.size();
        }
    }

    protected void setReportBase(ReportBase reportBase) {
        if (reportBase != null && this.reportBase == null) {
            this.reportBase = reportBase;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartUsed partUsed = (PartUsed) o;
        return Objects.equals(id, partUsed.id) && Objects.equals(reportBase, partUsed.reportBase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reportBase);
    }
}
