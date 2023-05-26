package pl.myworkspace.reportingapp.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.*;

@Entity
@Table(name = "parts_used")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PartUsed {

    @Id
    private UUID id;

    private int amount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partUsed")
    private Set<Part> partList;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;


    public PartUsed(int amount) {
        this.id = UUID.randomUUID();
        this.amount = amount;
        this.partList = new LinkedHashSet<>();
    }

    public void addPart(Part part) {
        if (part != null) {
            part.setPartUsed(this);
            partList.add(part);
        }
    }

    public void setReport(Report report) {
        if (report != null && this.report == null) {
            this.report = report;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartUsed partUsed = (PartUsed) o;
        return amount == partUsed.amount && Objects.equals(id, partUsed.id) && Objects.equals(partList, partUsed.partList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, partList);
    }
}
