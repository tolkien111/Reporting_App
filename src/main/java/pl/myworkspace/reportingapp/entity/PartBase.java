package pl.myworkspace.reportingapp.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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

    @ManyToMany(mappedBy = "partBaseList")
    private List<DeviceBase> deviceBaseList;

    public PartBase(@NonNull String name,
                    @NonNull String internalId,
                    int revision) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.internalId = internalId;
        this.revision = revision;
        this.deviceBaseList = new ArrayList<>();
    }

    public void addCompatibleDeviceBase(DeviceBase deviceBase) {
        if (deviceBase != null && !deviceBaseList.contains(deviceBase)) {
            deviceBaseList.add(deviceBase);
            deviceBase.getPartBaseList().add(this);
        }

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
