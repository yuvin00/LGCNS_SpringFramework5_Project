package mylab.user.di.annot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private SecurityService securityService;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public SecurityService getSecurityService() {
        return securityService;
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public boolean registerUser(
            String userId, String name, String password) {

        if (securityService.authenticate(userId, password)) {
            return userRepository.saveUser(userId, name);
        }

        return false;
    }
}