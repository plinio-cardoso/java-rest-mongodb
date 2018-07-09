package ie.dsch.services.controller;

import ie.dsch.services.model.ApplicationUser;
import ie.dsch.services.service.CustomUserDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@Api(value = "Users", description = "Users Service")
public class UserController {
    @Autowired
    private ErrorException errorException;

    @Autowired
    private CustomUserDetailsService userService;

    @ApiResponse(code = HttpServletResponse.SC_OK, message = "Returns all users")
    @ApiOperation(value = "Get a list of all users", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    public List<ApplicationUser> getUsers() {
        return userService.getUsers();
    }

    @ApiOperation(value = "Create a new user", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody ApplicationUser user) {
        try {
            userService.createUser(user);
        } catch (Exception error) {
            errorException.setMessage(error.getMessage());
            return ResponseEntity.badRequest().body(errorException);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
