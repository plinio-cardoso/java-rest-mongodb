package ie.dsch.services.controller;

import ie.dsch.services.model.Advert;
import ie.dsch.services.service.AdvertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/adverts")
@Api(value = "Adverts", description = "Advert Controller")
public class AdvertController {
    @Autowired
    AdvertService advertService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Retrieves a specific advert", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Advert> retrieveAdvert(@PathVariable(name = "id") String id) {
        Advert advert = advertService.findOne(id);

        if (advert == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(advert);
    }

    @GetMapping
    @ApiOperation(value = "Retrieves all adverts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Advert> retrieveAdverts() {
        return advertService.retrieveAdverts();
    }

    @PostMapping
    @ApiOperation(value = "Creates an advert", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createAdvert(@Valid @RequestBody Advert advert, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        advertService.createOrUpdateAdvert(advert);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Deletes a specific advert", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteAdvert(@PathVariable(name = "id") String id) {
        advertService.deleteAdvert(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping
    @ApiOperation(value = "Updates a specific advert", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateVariable(@Valid @RequestBody Advert advert, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        advertService.createOrUpdateAdvert(advert);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
