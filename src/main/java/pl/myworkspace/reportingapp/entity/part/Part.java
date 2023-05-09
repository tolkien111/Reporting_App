package pl.myworkspace.reportingapp.entity.part;


import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.myworkspace.reportingapp.entity.device.Device;


import java.util.ArrayList;
import java.util.List;
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


    @ManyToMany
    @JoinTable (name = "part_device",
            joinColumns = @JoinColumn (name = "part_id"),
            inverseJoinColumns = @JoinColumn (name = "device_id"))
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


}
