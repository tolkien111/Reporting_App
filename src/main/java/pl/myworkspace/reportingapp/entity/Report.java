package pl.myworkspace.reportingapp.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@DiscriminatorValue(value = "REPORT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Report extends ReportBase {

    public Report(@NonNull LocalDate reportDate, @NonNull String description) {
        super(reportDate, description);
    }
}
