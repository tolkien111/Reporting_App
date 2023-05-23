package pl.myworkspace.reportingapp.entity.part;


import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.myworkspace.reportingapp.entity.device.DeviceBase;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "parts_base")
@NoArgsConstructor
@Getter
public class PartBase {

    @Id
    private UUID id;

    private String name;
    private String internalId;
    private int revision;

    @ManyToMany (mappedBy = "partBaseList")
    private List<DeviceBase> deviceBaseList;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "partBase")
    private Part part;

    public PartBase(String name, String internalId, int revision) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.internalId = internalId;
        this.revision = revision;
        this.deviceBaseList = new ArrayList<>();
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartBase partBase = (PartBase) o;
        return revision == partBase.revision && Objects.equals(id, partBase.id) && Objects.equals(name, partBase.name) && Objects.equals(internalId, partBase.internalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, internalId, revision);
    }
}
