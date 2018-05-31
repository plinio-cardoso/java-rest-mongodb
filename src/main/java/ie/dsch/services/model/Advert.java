package ie.dsch.services.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document(collection = "adverts")
public class Advert {
    @Id
    private String id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Double price;
}
