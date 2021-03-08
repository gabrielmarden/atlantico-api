package br.com.testeatlantico.api.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import br.com.testeatlantico.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        br.com.testeatlantico.api.model.User user = userService.findByLogin(login);

        if (user==null)
            throw new UsernameNotFoundException("User not found with login: " + login);
        return new UserRepositoryUsersDetails(user);
    }


    private final static class UserRepositoryUsersDetails extends br.com.testeatlantico.api.model.User implements UserDetails{

        private UserRepositoryUsersDetails(User user){
            super(user);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public String getUsername() {
            return this.getLogin();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}