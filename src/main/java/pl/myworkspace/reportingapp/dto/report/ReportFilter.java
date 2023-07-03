package pl.myworkspace.reportingapp.dto.report;

import lombok.Builder;

@Builder
public record ReportFilter(String emailCompanyEmployee, DateFilter dateFilter) {
}
