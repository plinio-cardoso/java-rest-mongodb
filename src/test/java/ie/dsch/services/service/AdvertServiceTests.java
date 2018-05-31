package ie.dsch.services.service;

import ie.dsch.services.model.Advert;
import ie.dsch.services.repository.AdvertRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class AdvertServiceTests {
    private final String ADVERT_ID = "5b0f2a780819a0283e65cb8e";
    private final String ADVERT_TITLE = "House, Tralee, Co. Kerry";

    private final String ADVERT_DESCRIPTION = "Oustanding office space, with fit out works just complete and first " +
            "letting available. The property is fitted to a high specification and standard in a modern campus " +
            "type environment.";

    private final Double ADVERT_PRICE = 1900.00;

    @InjectMocks
    private AdvertService advertService;

    @Mock
    private AdvertRepository advertRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deleteAdvert() {
        doNothing().when(advertRepository).deleteById(this.ADVERT_ID);
        advertService.deleteAdvert(this.ADVERT_ID);
    }

    @Test
    public void findOneById() {
        Advert advert = Advert.builder()
                .title(this.ADVERT_TITLE)
                .description(this.ADVERT_DESCRIPTION)
                .price(this.ADVERT_PRICE)
                .build();

        when(advertRepository.findOneById(this.ADVERT_ID)).thenReturn(advert);

        Advert response = advertService.findOne(this.ADVERT_ID);

        assertEquals(response, advert);
    }

    @Test
    public void retrieveAdverts() {
        Advert advert = Advert.builder()
                .title(this.ADVERT_TITLE)
                .description(this.ADVERT_DESCRIPTION)
                .price(this.ADVERT_PRICE)
                .build();

        List<Advert> adverts = new ArrayList<>();
        adverts.add(advert);
        adverts.add(advert);

        when(advertRepository.findAll()).thenReturn(adverts);

        List<Advert> response = advertService.retrieveAdverts();

        assertEquals(2, response.size());
        assertEquals(response, adverts);
    }

    @Test
    public void createOrUpdateAdvert() {
        Advert advert = Advert.builder()
                .title(this.ADVERT_TITLE)
                .description(this.ADVERT_DESCRIPTION)
                .price(this.ADVERT_PRICE)
                .build();

        when(advertRepository.save(advert)).thenReturn(advert);

        advertService.createOrUpdateAdvert(advert);
    }
}
