package br.com.nkey.api.repository;

import br.com.nkey.api.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repositório de eventos
 */
public interface EventRepository extends MongoRepository<Event, String> {
}
