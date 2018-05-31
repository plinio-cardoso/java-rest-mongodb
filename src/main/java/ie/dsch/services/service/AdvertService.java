package ie.dsch.services.service;

import ie.dsch.services.model.Advert;
import ie.dsch.services.repository.AdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertService {
    @Autowired
    private AdvertRepository advertRepository;

    public List retrieveAdverts() {
        return advertRepository.findAll();
    }

    public void createAdvert(Advert advert) {
        advertRepository.save(advert);
    }
}
