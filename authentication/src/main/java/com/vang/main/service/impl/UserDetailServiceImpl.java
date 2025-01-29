package com.vang.main.service.impl;

import com.vang.main.entities.AuthInfo;
import com.vang.main.repositories.AuthInfoRepository;
import com.vang.main.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: UserDetailServiceImpl
 */
@Service
public class UserDetailServiceImpl implements UserDetailService {

    private final AuthInfoRepository authInfoRepository;

    @Autowired
    public UserDetailServiceImpl(AuthInfoRepository authInfoRepository) {
        this.authInfoRepository = authInfoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<GrantedAuthority> listGrant = null;
        AuthInfo authInfo = authInfoRepository.findByEmail(username);
        if(ObjectUtils.isEmpty(authInfo)) {

            throw new UsernameNotFoundException("Username is not found");
        }
        listGrant = List.of(new SimpleGrantedAuthority(authInfo.getRole()));
        return new User(authInfo.getEmail(), authInfo.getPassword(), listGrant);
    }
}