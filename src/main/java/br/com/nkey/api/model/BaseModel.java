package br.com.nkey.api.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

public class BaseModel {
    @Id
    private String id;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @JsonIgnore
    public boolean isNew(){
        return id == null;
    }
}
