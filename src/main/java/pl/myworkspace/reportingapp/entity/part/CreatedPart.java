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

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "part_id")
    private Part part;

    private String serialNumber;


    public CreatedPart(Part part, String serialNumber) {
        this.id = UUID.randomUUID();
        this.part = part;
        this.serialNumber = serialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatedPart createdPart = (CreatedPart) o;
        return Objects.equals(id, createdPart.id) && Objects.equals(part, createdPart.part) && Objects.equals(serialNumber, createdPart.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, part, serialNumber);
    }
}
