package ie.dsch.services.service;

import ie.dsch.services.model.User;
import ie.dsch.services.repository.UserRepository;

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

public class UserServiceTests {

    private final String USER_ID = "5b0f2a780819a0283e65cb8f";
    private final String FIRST_NAME = "Mike";
    private final String LAST_NAME = "Newman";
    private final String EMAIL = "mikenewman@gmail.com";
    private final String GENDER = "male";
    private final String IP_ADDRESS = "1.2.3.4";
    private User user = User.builder()
            .first_name(FIRST_NAME)
            .last_name(LAST_NAME)
            .email(EMAIL)
            .gender(GENDER)
            .ip_address(IP_ADDRESS)
            .build();
    private List<User> users = new ArrayList<>();
        

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void findUserById(){
        when(userRepository.findOneById(USER_ID)).thenReturn(user);
        User retrievedUser = userService.findOne(USER_ID);
        assertEquals(retrievedUser, user);
    }

    @Test
    public void retrieveUser() {
        users.add(user);
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);
        List<User> retrievedUsers = userService.retrieveUsers();
        assertEquals(2, retrievedUsers.size());
        assertEquals(retrievedUsers, users);
    }

    @Test
    public void createOrUpdateUser() {
        when(userRepository.save(user)).thenReturn(user);
        userService.createOrUpdateUser(user);
    }

    @Test
    public void deleteUser(){
        doNothing()
                .when(userRepository)
                .deleteById(USER_ID);
        userService.deleteUser(USER_ID);
    }


}
