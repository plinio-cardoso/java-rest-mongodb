package ie.dsch.services.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "adverts")
public class Advert {
    @Id
    private String id;

    @NotNull
    @Size(min = 10, max = 100, message = "Size of title is invalid (10 - 100)")
    private String title;

    @NotNull
    @Size(min = 100, max = 500, message = "Size of description is invalid (100 - 500)")
    private String description;

    @NotNull
    private Double price;
}
