package com.sptech.socialws.usecase;

import com.sptech.socialws.domain.Usuario;
import com.sptech.socialws.domain.dtos.UserDTO;
import com.sptech.socialws.domain.response.DefaultResponse;
import com.sptech.socialws.domain.response.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface AuthService extends UserDetailsService {

    ResponseEntity<Usuario> newUser(UserDTO user);

    ResponseEntity<LoginResponse> login(String username, String password);

    ResponseEntity<DefaultResponse> logout(int userId);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
