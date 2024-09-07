package com.rafaelcavalcante.encurtadorurl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "url")
public class Url {

    @Id
    private String id;

    private String urlCompleta;

    @Indexed(expireAfterSeconds = 0)
    private LocalDateTime tempoExpiracao;
}
