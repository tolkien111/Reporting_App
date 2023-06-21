package pl.myworkspace.reportingapp.entity;


import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Transactional
public class EntityTest {

    @Autowired
    protected EntityManager em;

    protected void persist(Object entity){
        em.persist(entity);
        em.flush();
        em.clear();
    }
}
