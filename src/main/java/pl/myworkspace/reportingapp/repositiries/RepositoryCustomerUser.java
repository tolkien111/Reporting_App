package pl.myworkspace.reportingapp.repositiries;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.myworkspace.reportingapp.entity.CustomerUser;

import java.util.UUID;

public interface RepositoryCustomerUser extends JpaRepository<CustomerUser, UUID> {

}
