package ie.dsch.services.controller;

import ie.dsch.services.model.User;
import ie.dsch.services.service.UserService;
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

@RestController
@RequestMapping("/v1/users")
@Api(value = "Users", description = "User Controller")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Retrieves a specific user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> retrieveUser(@PathVariable(name = "id") String id) {
        User user = userService.findOne(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping
    @ApiOperation(value = "Retrieves all users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> retrieveUsers() {
        return userService.retrieveUsers();
    }

    @PostMapping
    @ApiOperation(value = "Creates an user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        userService.createOrUpdateUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Deletes a specific user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteUser(@PathVariable(name = "id") String id) {
        userService.deleteUser(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping
    @ApiOperation(value = "Updates a specific user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        userService.createOrUpdateUser(user);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
