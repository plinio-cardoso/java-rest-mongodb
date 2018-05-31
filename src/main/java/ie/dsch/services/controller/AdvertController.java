package ie.dsch.services.controller;

import ie.dsch.services.model.Advert;
import ie.dsch.services.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/adverts")
public class AdvertController {
    @Autowired
    AdvertService advertService;

    @GetMapping("/{id}")
    public Advert retrieveAdvert(@PathVariable(name = "id") String id) {
        return advertService.findOne(id);
    }

    @GetMapping
    public List<Advert> retrieveAdverts() {
        return advertService.retrieveAdverts();
    }

    @PostMapping
    public ResponseEntity createAdvert(@Valid @RequestBody Advert advert) {
        advertService.createAdvert(advert);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteAdvert(@PathVariable(name = "id") String id) {
        advertService.deleteAdvert(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
