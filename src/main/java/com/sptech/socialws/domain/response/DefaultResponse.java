package com.sptech.socialws.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultResponse {

    private String message;
    private String code;

    public static final class Builder {
        private String message;
        private String code;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public DefaultResponse build() {
            DefaultResponse defaultResponse = new DefaultResponse();
            defaultResponse.setMessage(message);
            defaultResponse.setCode(code);
            return defaultResponse;
        }
    }
}
