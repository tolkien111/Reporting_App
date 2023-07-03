package pl.myworkspace.reportingapp.repositiries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.myworkspace.reportingapp.entity.Address;
import pl.myworkspace.reportingapp.entity.CustomerUser;

import java.util.UUID;


public interface RepositoryCustomerUser extends JpaRepository<CustomerUser, UUID> {

    @Query("SELECT (count (a) > 0) FROM Address a WHERE a = ?1")
    boolean addressExists(Address address);
}
