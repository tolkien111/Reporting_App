package pl.myworkspace.reportingapp.entity.report;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
final class WorkingTime {

    @Id
    private UUID id;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;


    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;

    public WorkingTime(UUID id, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public void setReport(Report report) {
        this.report = report;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkingTime that = (WorkingTime) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, startTime, endTime);
    }
}
