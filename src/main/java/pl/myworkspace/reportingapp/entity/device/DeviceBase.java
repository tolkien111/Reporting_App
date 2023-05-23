package pl.myworkspace.reportingapp.entity.device;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.myworkspace.reportingapp.entity.part.PartBase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "devices")
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@Getter
public class DeviceBase {

    @Id
    private UUID id;

    private String name;
    private String internalId;
    private int revision;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(name = "deviceBase_partBase",
            joinColumns = @JoinColumn(name = "deviceBase_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "partBase_id", referencedColumnName = "id"))
    private List<PartBase> partBaseList;


    public DeviceBase(String name, String internalId, int revision) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.internalId = internalId;
        this.revision = revision;
        this.partBaseList = new ArrayList<>();
    }

    public void addPartBase(PartBase partBase){
        partBaseList.add(partBase);
        partBase.getDeviceBaseList().add(this);
    }


}
