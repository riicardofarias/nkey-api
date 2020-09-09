package br.com.nkey.api.dto;

import br.com.nkey.api.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe responsável por transferir o resultado da
 * autenticação do usuário para a View
 */
public class LoginResponse {
    @JsonProperty("user")
    private final User user;

    @JsonProperty("token")
    private final String token;

    public LoginResponse(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }
    public String getToken() {
        return token;
    }
}
