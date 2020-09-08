package br.com.nkey.api.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

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

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
