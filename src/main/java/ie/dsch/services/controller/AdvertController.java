package ie.dsch.services.controller;

import ie.dsch.services.model.Advert;
import ie.dsch.services.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/adverts")
public class AdvertController {
    @Autowired
    AdvertService advertService;

    @GetMapping
    public String getAllAdverts() {
        return "Welcome!";
    }

    @PostMapping
    public ResponseEntity retrieveAdvert(@Valid @RequestBody Advert advert) {
        advertService.createAdvert(advert);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
