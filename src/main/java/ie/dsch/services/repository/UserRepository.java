package ie.dsch.services.repository;

import ie.dsch.services.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

    User findOneById(String id);


}

