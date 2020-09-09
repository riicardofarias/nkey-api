package br.com.nkey.api.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

/**
 * Entidade de usuários
 */
@Document("usuarios")
public class User extends BaseModel {
    @NotNull(message = "O nome do usuário deve ser informado")
    @Field("nome")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "O email do usuário deve ser informado")
    private String email;

    @NotNull(message = "A senha do usuário deve ser informada")
    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    @Field("senha")
    private String password;

    public User() {
    }

    public User(@NotNull String name, @NotNull String email, @NotNull String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Nome completo
     */
    public String getName() { return name; }

    /**
     * {@link User#getName()}
     */
    public void setName(String name) { this.name = name; }

    /**
     * E-mail
     */
    public String getEmail() { return email; }

    /**
     * {@link User#getEmail()}
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Senha
     */
    public String getPassword() { return password; }

    /**
     * {@link User#getPassword()}
     */
    public void setPassword(String password) { this.password = password; }
}
