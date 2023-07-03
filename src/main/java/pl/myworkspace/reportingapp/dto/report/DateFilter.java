package pl.myworkspace.reportingapp.dto.report;


import lombok.Builder;

import java.time.LocalDate;

@Builder
public record DateFilter(LocalDate fromDate, LocalDate toDate) {
}
