package br.com.nkey.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document("eventos")
public class Event extends BaseModel {
    @NotNull(message = "O nome do evento deve ser informado")
    @Field(name = "nome")
    @JsonProperty("nome")
    private String name;

    @NotNull(message = "A data do evento deve ser informada")
    @JsonProperty("data")
    @Field(name = "data")
    private Date date;

    @DBRef
    private User user;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
