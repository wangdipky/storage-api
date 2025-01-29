package com.vang.main.service.impl;

import com.vang.main.constant.BaseConstant;
import com.vang.main.entities.AuthInfo;
import com.vang.main.entities.SecretKey;
import com.vang.main.repositories.AuthInfoRepository;
import com.vang.main.repositories.SecretKeyRepository;
import com.vang.main.service.UserService;
import com.vang.main.util.CoreUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${jwt.secret.key}")
    private String secret;

    private final AuthInfoRepository authInfoRepository;

    private final SecretKeyRepository secretKeyRepository;

    @Autowired
    public UserServiceImpl(AuthInfoRepository authInfoRepository, SecretKeyRepository secretKeyRepository) {
        this.authInfoRepository = authInfoRepository;
        this.secretKeyRepository = secretKeyRepository;
    }

    @Override
    public String generateSecretKey(long expiredDate) {

        SecretKey secretKey = new SecretKey();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String, Object> claim = new HashMap<>();
        claim.put(BaseConstant.KEY_USERNAME, userDetails.getUsername());
        claim.put(BaseConstant.KEY_CREATED, CoreUtils.getSystemDateTime());
        String token = Jwts
                .builder()
                .expiration(new Date(expiredDate))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secret)))
                .claims(claim)
                .compact();

        //save data
        AuthInfo authInfo = authInfoRepository.findByEmail(userDetails.getUsername());
        secretKey.setAuthId(authInfo.getId());
        secretKey.setSecretKey(token);
        secretKey.setCreateDate(CoreUtils.getSystemDateTime());
        secretKey.setExpiredDate(new Date(expiredDate));

        secretKeyRepository.save(secretKey);
        return StringUtils.isEmpty(token) ? null : token;
    }
}