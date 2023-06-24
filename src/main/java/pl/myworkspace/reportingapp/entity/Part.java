package pl.myworkspace.reportingapp.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "parts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Part {

    @Id
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "part_base_id")
    private PartBase partBase;

    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "part_used_id")
    private PartUsed partUsed;

    public Part(@NonNull PartBase partBase,
                @NonNull String serialNumber) {
        this.id = UUID.randomUUID();
        this.partBase = partBase;
        this.serialNumber = serialNumber;
    }

    protected void setPartUsed(PartUsed partUsed) {
        if (partUsed != null && this.partUsed == null) {
            this.partUsed = partUsed;
        }
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
