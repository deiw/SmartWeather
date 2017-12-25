package pl.majorczyk.weatherapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.majorczyk.weatherapp.model.user.User;
import pl.majorczyk.weatherapp.model.user.UserRole;
import pl.majorczyk.weatherapp.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository repo;

    @Autowired
    public void setRepo(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=repo.findUserByEmail(s);
        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                convertAuthorities(user.getRoles())
        );
    }
    private Set<GrantedAuthority> convertAuthorities(Set<UserRole> roles){
        Set<GrantedAuthority> authorities=new HashSet<>();
        for(UserRole role:roles){
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }
}
