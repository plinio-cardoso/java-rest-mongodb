package ie.dsch.services.service;

import ie.dsch.services.model.Advert;
import ie.dsch.services.repository.AdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertService {
    @Autowired
    private AdvertRepository advertRepository;

    public List<Advert> retrieveAdverts() {
        return advertRepository.findAll();
    }

    public Advert findOne(String id) {
        return advertRepository.findOneById(id);
    }

    public void createOrUpdateAdvert(Advert advert) {
        advertRepository.save(advert);
    }

    public void deleteAdvert(String id) {
        advertRepository.deleteById(id);
    }
}
