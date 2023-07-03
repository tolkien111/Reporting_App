package pl.myworkspace.reportingapp.repositiries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.myworkspace.reportingapp.entity.CompanyEmployee;
import pl.myworkspace.reportingapp.entity.CompanyManager;
import pl.myworkspace.reportingapp.entity.CompanyUser;



import java.util.List;
import java.util.UUID;


public interface RepositoryCompanyUser extends JpaRepository<CompanyUser, UUID> {

    @Query("FROM CompanyManager c ORDER BY c.lastName ASC")
    List<CompanyManager> findAllCompanyManagers();

    @Query("FROM CompanyEmployee c ORDER BY c.lastName ASC")
    List<CompanyEmployee> findAllCompanyEmployees();

}
