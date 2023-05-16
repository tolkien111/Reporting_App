package pl.myworkspace.reportingapp.entity.part;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.myworkspace.reportingapp.entity.report.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "used_parts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UsedPart {

    @Id
    private UUID id;

    private int amount;




    @OneToMany (cascade = CascadeType.ALL, mappedBy = "usedPart")
    private List<CreatedPart> createdPartList;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;


    public UsedPart(int amount) {
        this.id = UUID.randomUUID();
        this.amount = amount;
        this.createdPartList = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsedPart usedPart = (UsedPart) o;
        return amount == usedPart.amount && Objects.equals(id, usedPart.id) && Objects.equals(createdPartList, usedPart.createdPartList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, createdPartList);
    }
}
