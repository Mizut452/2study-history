package Mizut452.studyhistory;
import Mizut452.studyhistory.HomeController.UserList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginUserDetailsService implements UserDetailsService {
    private final LoginUserRepository repo;

    public LoginUserDetailsService(LoginUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserList> userOp = repo.findByUsername(username);
        return userOp.map(userLists -> new LoginUserDetails(userLists))
                .orElseThrow(()-> new UsernameNotFoundException("not found"));


    }


}
