package pl.myworkspace.reportingapp.repositiries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.myworkspace.reportingapp.entity.CompanyEmployee;
import pl.myworkspace.reportingapp.entity.CompanyManager;
import pl.myworkspace.reportingapp.entity.CompanyUser;



import java.util.List;
import java.util.UUID;

public interface RepositoryCompanyUser extends JpaRepository<CompanyUser, UUID> {

    @Query("FROM CompanyUser c WHERE c.userType = pl.myworkspace.reportingapp.entity.enums.CompanyUserType.COMPANY_MANAGER")
    List<CompanyManager> findAllCompanyManagers();

    @Query("FROM CompanyUser c WHERE c.userType = pl.myworkspace.reportingapp.entity.enums.CompanyUserType.COMPANY_EMPLOYEE")
    List<CompanyEmployee> findAllCompanyEmployees();

}
