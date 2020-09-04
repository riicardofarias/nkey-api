package br.com.nkey.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class User {
    @Id @Getter @Setter
    private String id;

    @Getter @Setter
    private String name;
}
