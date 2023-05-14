package com.anywr.School.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.anywr.School.dto.UserDto;
import com.anywr.School.entities.UserDao;
import com.anywr.School.repositories.UserRepository;




@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userDao;
    
    private final PasswordEncoder bcryptEncoder;

    @Autowired
    @Lazy
    public JwtUserDetailsService(PasswordEncoder passwordEncoder) {
        this.bcryptEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	UserDao user = userDao.findByUsername(username);
    	if (user == null) {
    		throw new UsernameNotFoundException("User not found with username: " + username);
    	}
    	return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
    			new ArrayList<>());
    }

    public UserDao save(UserDto user) {
    	UserDao newUser = new UserDao();
    	newUser.setUsername(user.getUsername());
    	newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
    	return userDao.save(newUser);
    }  }