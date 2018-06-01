package ie.dsch.services.service;

import ie.dsch.services.model.User;
import ie.dsch.services.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> retrieveUsers() {
        return userRepository.findAll();
    }

    public User findOne(String id) {
        return userRepository.findOneById(id);
    }

    public void createOrUpdateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
    
}
