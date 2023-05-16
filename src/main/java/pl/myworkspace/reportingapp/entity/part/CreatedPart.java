package pl.myworkspace.reportingapp.entity.part;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "created_parts")
@NoArgsConstructor
@Getter
public class CreatedPart {

    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "part_id")
    private Part part;

    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "used_part_id")
    private UsedPart usedPart;

    public CreatedPart(Part part, String serialNumber) {
        this.id = UUID.randomUUID();
        this.part = part;
        this.serialNumber = serialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatedPart that = (CreatedPart) o;
        return Objects.equals(id, that.id) && Objects.equals(part, that.part) && Objects.equals(serialNumber, that.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, part, serialNumber);
    }
}
