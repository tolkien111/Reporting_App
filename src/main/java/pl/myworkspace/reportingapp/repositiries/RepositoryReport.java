package pl.myworkspace.reportingapp.repositiries;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pl.myworkspace.reportingapp.dto.ReportsByUser;
import pl.myworkspace.reportingapp.dto.report.ReportFilter;
import pl.myworkspace.reportingapp.entity.Report;

import java.util.Optional;
import java.util.UUID;


public interface RepositoryReport extends JpaRepository<Report, UUID>, JpaSpecificationExecutor<Report> {


   //Optional<Page<ReportsByUser>> findAllByEmployeeAndDate(Specification<ReportFilter> specification, Pageable page);

}
