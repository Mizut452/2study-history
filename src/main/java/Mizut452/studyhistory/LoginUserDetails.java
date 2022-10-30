package Mizut452.studyhistory;

import Mizut452.studyhistory.HomeController.UserList;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class LoginUserDetails implements UserDetails {
    private final UserList userLists;

    public LoginUserDetails(UserList userLists) {
        this.userLists = userLists;
    }

    public UserList getUserList() {
        return userLists;
    }

    @Override
    public String getPassword() {
        return userLists.password();
    }

    @Override
    public String getUsername() {
        return userLists.username();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
