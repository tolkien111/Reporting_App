package pl.myworkspace.reportingapp.repositiries;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.myworkspace.reportingapp.dto.ReportsByUser;
import pl.myworkspace.reportingapp.dto.report.ReportFilter;
import pl.myworkspace.reportingapp.entity.Report;

import java.util.Optional;
import java.util.UUID;


public interface RepositoryReport extends JpaRepository<Report, UUID> {


   Optional<Page<ReportsByUser>> findAllByUserAndDate(Specification<ReportFilter> specification, Pageable page);

}
