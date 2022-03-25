package com.sptech.socialws.usecase.impl;

import com.sptech.socialws.domain.Usuario;
import com.sptech.socialws.domain.exception.InvalidFieldException;
import com.sptech.socialws.domain.exception.UserNotFoundException;
import com.sptech.socialws.gateway.repository.UserRepository;
import com.sptech.socialws.usecase.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unused")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<String> updateField(
            Integer userId,
            String field,
            String newValue
    ) {
        Usuario user = userRepository
                .findById(userId)
                .orElseThrow(
                        UserNotFoundException::new
                );

        switch (field) {
            case "EMAIL":
                user.setEmail(newValue);
                break;
            case "FULLNAME":
                user.setFullname(newValue);
                break;
            case "TELEPHONE":
                user.setTelephone(newValue);
                break;
            case "ARTISTIC_NAME":
                user.setArtisticName(newValue);
                break;
            case "PASSWORD":
                user.setPassword(newValue);
                break;
            case "IS_ARTIST":
                user.setIsArtist(!user.getIsArtist());
                break;
            default:
                throw new InvalidFieldException();
        }

        userRepository.save(user);

        return ResponseEntity
                .ok()
                .build();
    }

}
