package br.com.nkey.api.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

/**
 * Representa a base de todas as entidades
 */
public class BaseModel {
    @Id
    private String id;

    /**
     * Id da entidade
     */
    public String getId() { return id; }

    /**
     * {@link BaseModel#getId()}
     */
    public void setId(String id) { this.id = id; }

    /**
     * Método que verifica se a classe foi persisitida no banco de dados
     * Retorna true se a classe não tiver sido persistida
     * @return bool
     */
    @JsonIgnore
    public boolean isNew(){
        return id == null;
    }
}
