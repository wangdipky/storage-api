package com.vang.main.test;

import com.vang.main.repositories.AuthInfoRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TestRest {

    private AuthInfoRepository authInfoRepository;

    @Autowired
    public TestRest(AuthInfoRepository authInfoRepository) {
        this.authInfoRepository = authInfoRepository;
    }

    @PostMapping("/generate-password")
    public String generatePassword(@RequestParam("password") String password) {

        return new BCryptPasswordEncoder().encode(password);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/api")
    public ResponseEntity<Object> testCall(HttpServletRequest request, HttpServletResponse response) {

        String jwt = request.getHeader("Authorization");
        return new ResponseEntity<>(authInfoRepository.findAll(), HttpStatus.OK);
    }
}
