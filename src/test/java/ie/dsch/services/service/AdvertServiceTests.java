package ie.dsch.services.service;

import ie.dsch.services.model.Advert;
import ie.dsch.services.repository.AdvertRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class AdvertServiceTests {
    @InjectMocks
    private AdvertService advertService;

    @Mock
    private AdvertRepository advertRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createAdvert() {
       Advert advert = Advert.builder()
                .title("Test")
                .description("Bla bla bla")
                .price(1900.00)
                .build();

        when(advertRepository.save(advert)).thenReturn(advert);

        advertService.createAdvert(advert);
    }
}
