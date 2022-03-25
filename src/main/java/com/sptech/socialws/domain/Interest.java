package com.sptech.socialws.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_interests")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_interest")
    private Integer idInterest;

    @Column(name = "ds_interest")
    private String value;

    @OneToMany(mappedBy = "interest")
    private List<UserInterests> interests;

}
