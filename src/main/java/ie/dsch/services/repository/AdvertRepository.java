package ie.dsch.services.repository;

import ie.dsch.services.model.Advert;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertRepository extends MongoRepository<Advert, String> {

}
