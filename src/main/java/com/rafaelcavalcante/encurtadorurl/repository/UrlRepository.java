package com.rafaelcavalcante.encurtadorurl.repository;

import com.rafaelcavalcante.encurtadorurl.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<Url, String> {
}
