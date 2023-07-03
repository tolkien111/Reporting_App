package pl.myworkspace.reportingapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@ToString
public final class WorkingTime {

    @Id
    private UUID id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;


    @ManyToOne
    @JoinColumn(name = "reportBase_id")
    private ReportBase reportBase;

    public WorkingTime(@NonNull LocalDate date,
                       @NonNull LocalTime startTime,
                       @NonNull LocalTime endTime) {
        this.id = UUID.randomUUID();
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reportBase = null;
    }

    void setReportBase(ReportBase reportBase) {
        if (reportBase != null && this.reportBase == null) {
            this.reportBase = reportBase;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkingTime that = (WorkingTime) o;
        return Objects.equals(date, that.date) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && Objects.equals(reportBase, that.reportBase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, startTime, endTime, reportBase);
    }
}
