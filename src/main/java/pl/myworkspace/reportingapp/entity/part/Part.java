package pl.myworkspace.reportingapp.entity.part;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "parts")
@NoArgsConstructor
@Getter
public class Part {

    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "partBase_id")
    private PartBase partBase;

    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "usedPart_id")
    private UsedPart usedPart;

    public Part(PartBase partBase, String serialNumber) {
        this.id = UUID.randomUUID();
        this.partBase = partBase;
        this.serialNumber = serialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part that = (Part) o;
        return Objects.equals(id, that.id) && Objects.equals(partBase, that.partBase) && Objects.equals(serialNumber, that.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, partBase, serialNumber);
    }
}
