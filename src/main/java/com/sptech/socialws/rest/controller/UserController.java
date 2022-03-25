package com.sptech.socialws.rest.controller;

import com.sptech.socialws.domain.dtos.UpdateFieldDTO;
import com.sptech.socialws.usecase.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/update-field")
    public ResponseEntity<String> updateField(@RequestBody UpdateFieldDTO updateField){
        return userService.updateField(
                updateField.getUserId(),
                updateField.getField(),
                updateField.getNewValue()
        );
    }
}
