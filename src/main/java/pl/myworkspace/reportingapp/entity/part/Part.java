package pl.myworkspace.reportingapp.entity.part;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class Part {

    @Id
    private UUID id;

    private String interlalId;

    @OneToOne
    @JoinColumn (name = "part_name_id")
    private PartName partName;

}
