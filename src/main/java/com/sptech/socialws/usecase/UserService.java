package com.sptech.socialws.usecase;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ResponseEntity<String> updateField(Integer userId, String field, String newValue);

}
