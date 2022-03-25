package com.sptech.socialws.domain.response;

import com.sptech.socialws.domain.UserInterests;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private Integer userId;
    private String fullname;
    private String artisticName = null;
    private String telephone;
    private String email;
    private String cpf;
    private Integer birthdate;
    private Boolean isArtist;
    private List<String> interests;

    public static final class Builder {
        private Integer userId;
        private String fullname;
        private String artisticName = null;
        private String telephone;
        private String email;
        private String cpf;
        private Integer birthdate;
        private Boolean isArtist;
        private List<String> interests;


        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Builder withFullname(String fullname) {
            this.fullname = fullname;
            return this;
        }

        public Builder withArtisticName(String artisticName) {
            this.artisticName =
                    this.artisticName == null ? "Sem nome artístico" : artisticName ;

            return this;
        }

        public Builder withTelephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
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
            this.isArtist = isArtist;
            return this;
        }

        public Builder withInterests(List<String> interests){
            this.interests = interests;
            return this;
        }


        public LoginResponse build() {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setUserId(userId);
            loginResponse.setFullname(fullname);
            loginResponse.setArtisticName(artisticName);
            loginResponse.setTelephone(telephone);
            loginResponse.setEmail(email);
            loginResponse.setCpf(cpf);
            loginResponse.setBirthdate(birthdate);
            loginResponse.setIsArtist(isArtist);
            loginResponse.setInterests(interests);
            return loginResponse;
        }
    }
}
