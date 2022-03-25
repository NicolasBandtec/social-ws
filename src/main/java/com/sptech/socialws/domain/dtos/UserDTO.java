package com.sptech.socialws.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String fullname;
    private String artisticName = null;
    private String telephone;
    private String email;
    private String password;
    private String cpf;
    private Integer birthdate;
    private Boolean isArtist;
    private List<Integer> interests;

}
