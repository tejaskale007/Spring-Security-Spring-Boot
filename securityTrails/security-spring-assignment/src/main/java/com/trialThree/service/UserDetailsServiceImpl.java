/**
 * UserDetailsServiceImpl : This class is responsible for providing the implementation
 *                          of UserDetailsService class.
 */
package com.trialThree.service;

import com.trialThree.model.User;
import com.trialThree.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //Fetching the data from database by email
        User user = userRepository.findByEmail(email);

        if (user == null)
            throw new UsernameNotFoundException("User Not Found : " + email);

        return new CustomUserDetailService(user);
    }
}
