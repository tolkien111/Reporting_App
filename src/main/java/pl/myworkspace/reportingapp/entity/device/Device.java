package pl.myworkspace.reportingapp.entity.device;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.myworkspace.reportingapp.entity.part.Part;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "devices")
@NoArgsConstructor
@Getter
public class Device {

    @Id
    private UUID id;

    private String name;
    private String internalId;
    private int revision;

    @ManyToMany(mappedBy = "deviceList")
    private List<Part> partList;

    public Device(String name, String internalId, int revision) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.internalId = internalId;
        this.revision = revision;
        this.partList = new ArrayList<>();
    }

}
