package com.codegym.casestudy_module4.service.impl;

import com.codegym.casestudy_module4.dto.UserInfoUserDetails;
import com.codegym.casestudy_module4.entity.User;
import com.codegym.casestudy_module4.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInforDetailService implements UserDetailsService {
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User appUser = iUserRepository.findByUsername(username);
        if(appUser == null) {
            throw new UsernameNotFoundException("User not found!");
        }
//        Lấy tất cả role của AppUser
        UserInfoUserDetails infoUserDetails = new UserInfoUserDetails(appUser);
        return infoUserDetails;
    }
}
