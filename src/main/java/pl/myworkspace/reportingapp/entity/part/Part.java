package pl.myworkspace.reportingapp.entity.part;


import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.myworkspace.reportingapp.entity.device.Device;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "parts")
@NoArgsConstructor
@Getter
public class Part {

    @Id
    private UUID id;

    private String name;
    private String internalId;
    private int revision;

    @ManyToMany (mappedBy = "partList")
    private List<Device> deviceList;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "part")
    private CreatedPart createdPart;

    public Part(String name, String internalId, int revision) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.internalId = internalId;
        this.revision = revision;
        this.deviceList = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return revision == part.revision && Objects.equals(id, part.id) && Objects.equals(name, part.name) && Objects.equals(internalId, part.internalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, internalId, revision);
    }
}
