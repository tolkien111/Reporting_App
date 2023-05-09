package pl.myworkspace.reportingapp.entity.report;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
final class WorkingTime {

    @Id
    private UUID id;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Duration duration;

    @ManyToOne
    private Report report;

    public WorkingTime(LocalDateTime startDateTime, LocalDateTime endDateTime, Duration duration) {
        this.id = UUID.randomUUID();
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkingTime that = (WorkingTime) o;
        return Objects.equals(id, that.id) && Objects.equals(startDateTime, that.startDateTime) && Objects.equals(endDateTime, that.endDateTime) && Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDateTime, endDateTime, duration);
    }
}
