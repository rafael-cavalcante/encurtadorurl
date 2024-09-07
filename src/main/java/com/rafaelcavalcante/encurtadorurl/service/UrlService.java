package com.rafaelcavalcante.encurtadorurl.service;

import com.rafaelcavalcante.encurtadorurl.model.Url;
import com.rafaelcavalcante.encurtadorurl.model.dto.UrlReponse;
import com.rafaelcavalcante.encurtadorurl.model.dto.UrlRequest;
import com.rafaelcavalcante.encurtadorurl.repository.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;

@Service
public class UrlService {

    private UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlReponse criarUrlEncurtada(UrlRequest urlRequest, HttpServletRequest httpServletRequest) {
        String id;

        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (this.urlRepository.existsById(id));

        this.urlRepository.save(new Url(id, urlRequest.url(), LocalDateTime.now().plusMinutes(1)));

        var redirectUrl = httpServletRequest.getRequestURL().toString().replace("encurtador-url", id);

        return new UrlReponse(redirectUrl);
    }

    public ResponseEntity<Void> buscar(String id){
        var url = this.urlRepository.findById(id);

        if(url.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.get().getUrlCompleta()));

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }
}
