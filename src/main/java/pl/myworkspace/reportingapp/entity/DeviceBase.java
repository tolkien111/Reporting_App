package pl.myworkspace.reportingapp.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "devices_base")
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor (access = AccessLevel.PROTECTED)
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


    public DeviceBase(@NonNull String name,
                      @NonNull String internalId,
                      int revision) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.internalId = internalId;
        this.revision = revision;
        this.partBaseList = new ArrayList<>();
    }

    public void addCompatiblePartBase(PartBase partBase){
        if(partBase != null && !partBaseList.contains(partBase)) {
            partBaseList.add(partBase);
            partBase.getDeviceBaseList().add(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceBase that = (DeviceBase) o;
        return revision == that.revision && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(internalId, that.internalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, internalId, revision);
    }
}
