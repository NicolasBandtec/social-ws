package com.sptech.socialws.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_user_interests")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInterests {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUserInterest;

    @ManyToOne
    @JoinColumn(name = "id_interest")
    private Interest interest;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_user")
    private Usuario usuario;

    public String getInterest(){
        return interest.getValue();
    }

    public static final class Builder {
        private Interest interest;
        private Usuario usuario;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withInterest(Interest interest) {
            this.interest = interest;
            return this;
        }

        public Builder withUsuario(Usuario usuario) {
            this.usuario = usuario;
            return this;
        }

        public UserInterests build() {
            UserInterests userInterests = new UserInterests();
            userInterests.setInterest(interest);
            userInterests.setUsuario(usuario);
            return userInterests;
        }
    }
}
