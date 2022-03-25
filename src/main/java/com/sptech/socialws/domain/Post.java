package com.sptech.socialws.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer idPost;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private Usuario user;

    @Column(name = "ds_comment")
    private String descricao;

    @Column(name = "dh_insert")
    private LocalDateTime dataHora;


    public static final class Builder {
        private Usuario user;
        private String descricao;
        private LocalDateTime dataHora = LocalDateTime.now();

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withUser(Usuario user) {
            this.user = user;
            return this;
        }

        public Builder withDescricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public Builder withDataHora() {
            this.dataHora = LocalDateTime.now();
            return this;
        }

        public Post build() {
            Post post = new Post();
            post.setUser(user);
            post.setDescricao(descricao);
            post.setDataHora(dataHora);
            return post;
        }
    }
}
