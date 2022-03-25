package com.sptech.socialws.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private Integer idPost;
    private Integer userId;
    private String userFullName;
    private String descricao;
    private LocalDateTime dataHora;


    public static final class Builder {
        private Integer idPost;
        private Integer userId;
        private String userFullName;
        private String descricao;
        private LocalDateTime dataHora;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withIdPost(Integer idPost) {
            this.idPost = idPost;
            return this;
        }

        public Builder withUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Builder withUserFullName(String userFullName) {
            this.userFullName = userFullName;
            return this;
        }

        public Builder withDescricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public Builder withDataHora(LocalDateTime dataHora) {
            this.dataHora = dataHora;
            return this;
        }

        public PostResponse build() {
            PostResponse postResponse = new PostResponse();
            postResponse.setIdPost(idPost);
            postResponse.setUserId(userId);
            postResponse.setUserFullName(userFullName);
            postResponse.setDescricao(descricao);
            postResponse.setDataHora(dataHora);
            return postResponse;
        }
    }
}
