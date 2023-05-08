package pl.myworkspace.reportingapp.entity.part;


import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public final class PartName {

    @Id
    private UUID id;

    private String partName;
    private String revision;

    @OneToOne (cascade = CascadeType.ALL, mappedBy = "partName")
    private Part part;

}
