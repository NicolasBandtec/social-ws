package com.sptech.socialws.rest.controller;

import com.sptech.socialws.domain.Usuario;
import com.sptech.socialws.domain.dtos.LoginDTO;
import com.sptech.socialws.domain.dtos.UserDTO;
import com.sptech.socialws.domain.response.DefaultResponse;
import com.sptech.socialws.domain.response.LoginResponse;
import com.sptech.socialws.usecase.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/authentication")
@SuppressWarnings("unused")
public class AuthController {

    @Autowired
    private AuthService userService;

    @PostMapping("/create")
    public ResponseEntity<Usuario> newUser(@RequestBody @Valid UserDTO user){
        return userService.newUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDto){
        return userService
                .login(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                );
    }

    @PostMapping("/logout/{id}")
    public ResponseEntity<DefaultResponse> logout(@PathVariable int id){
        return userService.logout(id);
    }

}
