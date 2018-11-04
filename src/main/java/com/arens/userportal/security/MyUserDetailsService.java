package com.arens.userportal.security;

import com.arens.userportal.entity.User;
import com.arens.userportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private UserService userService;

    public MyUserDetailsService() {
        super();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final User user = userService.findByName(username);

        if (user == null) {
            throw new UsernameNotFoundException(" Username was not found : " + username);
        }

        final List<GrantedAuthority> auths = AuthorityUtils.createAuthorityList("Admin");
        return new org.springframework.security.core.userdetails.User(user.getLastName(), "123", auths);
    }
}
