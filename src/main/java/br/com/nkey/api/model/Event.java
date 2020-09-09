package br.com.nkey.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Entidade de eventos
 */
@Document("eventos")
public class Event extends BaseModel {
    @NotNull(message = "O nome do evento deve ser informado")
    @Field(name = "nome")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "A data do evento deve ser informada")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("date")
    @Field(name = "data")
    private Date date;

    @DBRef
    private User user;

    /**
     * Nome do evento
     */
    public String getName() { return name; }

    /**
     * {@link Event#getName()}
     */
    public void setName(String name) { this.name = name; }

    /**
     * Data do evento
     */
    public Date getDate() { return date; }

    /**
     * {@link Event#getDate()}
     */
    public void setDate(Date date) { this.date = date; }

    /**
     * Usuário a quem o evento pertence
     */
    public User getUser() { return user; }

    /**
     * {@link Event#getUser()}
     */
    public void setUser(User user) { this.user = user; }

    /**
     * Data do evento formatada (dd/MM/yyyy) Ex: 09/09/2020
     */
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty(value = "formattedDate")
    public Date getFormattedDate() {
        return date;
    }
}
