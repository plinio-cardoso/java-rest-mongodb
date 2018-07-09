package ie.dsch.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class ApplicationUser {
    @Id
    private String id;

    @NotNull(message = "Username must be present")
    @Size(min = 6, max = 15, message = "Size of title is invalid (6 - 15)")
    private String username;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 6, max = 15, message = "Size of password is invalid (6 - 15)")
    private String password;

    @NotNull
    private String role;
}
