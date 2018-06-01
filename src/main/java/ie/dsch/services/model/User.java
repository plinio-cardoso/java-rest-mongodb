package ie.dsch.services.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String first_name;
    private String last_name;
    private String email;
    private String gender;
    private String ip_address;
}
