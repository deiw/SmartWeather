package pl.majorczyk.weatherapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.majorczyk.weatherapp.model.user.User;
import pl.majorczyk.weatherapp.model.user.UserRole;
import pl.majorczyk.weatherapp.repository.UserRepository;
import pl.majorczyk.weatherapp.repository.UserRoleRepository;

@Service
public class UserService {

    private static final String DEFAULT_ROLE="ROLE_USER";

    private UserRepository userRepo;
    private UserRoleRepository roleRepo;

    @Autowired
    public void setUserRepo(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    @Autowired
    public void setRoleRepo(UserRoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    public void addUser(User user){
        UserRole role=roleRepo.findByRole(DEFAULT_ROLE);
        user.getRoles().add(role);
        userRepo.save(user);
    }
}
