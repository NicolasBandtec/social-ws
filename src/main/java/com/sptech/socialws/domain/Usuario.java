package com.sptech.socialws.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_user")
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer userId;

    @Column(name = "vl_fullname")
    @NotEmpty(message = "Campo de nome obrigatório!")
    private String fullname;

    @Column(name = "vl_email")
    @NotEmpty(message = "Campo e-mail obrigatório!")
    @Email(message = "E-mail inválido!")
    private String email;

    @Column(name = "vl_telephone")
    @NotEmpty(message = "Telefone obrigatório!")
    private String telephone;

    @Column(name = "vl_password")
    @NotEmpty(message = "Senha obrigatória!")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "vl_artistic_name")
    private String artisticName;

    @Column(name = "vl_birthdate")
    @NotNull(message = "Data de nascimento obrigatória!")
    private Integer birthdate;

    @Column(name = "vl_document")
    @CPF(message = "CPF Inválido")
    private String cpf;

    @Column(name = "is_artist")
    private Boolean isArtist;

    @Column(name = "is_connected")
    private boolean isConnected;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<UserInterests> interests;

    public static final class Builder {
        private String fullname;
        private String email;
        private String telephone;
        private String password;
        private String artisticName;
        private String cpf;
        private Integer birthdate;
        private boolean isArtist;
        private boolean isConnected;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withFullname(String fullname) {
            this.fullname = fullname;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withTelephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withArtisticName(String artisticName) {
            this.artisticName = artisticName;
            return this;
        }

        public Builder withCpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder withBirthdate(Integer birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public Builder withIsArtist(Boolean isArtist) {
            this.isArtist = Objects.requireNonNullElse(isArtist, false);
            return this;
        }

        public Builder withIsConnected(Boolean isConnected) {
            this.isConnected = isConnected;
            return this;
        }

        public Usuario build() {
            Usuario user = new Usuario();
            user.setFullname(fullname);
            user.setEmail(email);
            user.setTelephone(telephone);
            user.setPassword(password);
            user.setArtisticName(artisticName);
            user.setCpf(cpf);
            user.setBirthdate(birthdate);
            user.setIsArtist(isArtist);
            user.setConnected(isConnected);
            return user;
        }
    }
}
