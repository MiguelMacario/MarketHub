package com.macariomiguel.ecommerceproject.controller;

import com.macariomiguel.ecommerceproject.dto.AuthenticationDTO;
import com.macariomiguel.ecommerceproject.dto.LoginResponseDTO;
import com.macariomiguel.ecommerceproject.dto.RegisterDTO;
import com.macariomiguel.ecommerceproject.entity.User;
import com.macariomiguel.ecommerceproject.security.TokenService;
import com.macariomiguel.ecommerceproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UserService service, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = service;
        this.tokenService = tokenService;
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Validated AuthenticationDTO data) {
        var userPassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(userPassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("register")
    public ResponseEntity<Void> register(@RequestBody @Validated RegisterDTO data){
        if(this.userService.findByLogin(data.login())!=null){return ResponseEntity.badRequest().build();}

        userService.register(data);
        return ResponseEntity.ok().build();
    }

}
