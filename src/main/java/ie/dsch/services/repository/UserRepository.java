package ie.dsch.services.repository;

import ie.dsch.services.model.ApplicationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserRepository extends CrudRepository<ApplicationUser, Integer> {
    @Override
    ArrayList<ApplicationUser> findAll();

    ApplicationUser findByUsername(String username);

    Boolean existsByUsername(String username);
}
