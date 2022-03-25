package com.sptech.socialws.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFieldDTO {
    private Integer userId;
    private String field;
    private  String newValue;
}
