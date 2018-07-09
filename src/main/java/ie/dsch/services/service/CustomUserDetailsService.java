package ie.dsch.services.service;

import ie.dsch.services.model.ApplicationUser;
import ie.dsch.services.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = loadApplicationUserByUsername(username);

        return new User(
                applicationUser.getUsername(),
                applicationUser.getPassword(),
                AuthorityUtils.createAuthorityList(applicationUser.getRole())
        );
    }

    public ApplicationUser loadApplicationUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public ApplicationUser createUser(ApplicationUser user) throws Exception {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new Exception("There is an account with that username: " + user.getUsername());
        }

        user.setPassword(this.passwordEncode(user.getPassword()));

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new Exception(e.getMessage());
        }

        return user;
    }

    public List<ApplicationUser> getUsers() {
        return userRepository.findAll();
    }

    public String passwordEncode(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return passwordEncoder.encode(password);
    }
}