package com.rafaelcavalcante.encurtadorurl.service;

import com.rafaelcavalcante.encurtadorurl.model.Url;
import com.rafaelcavalcante.encurtadorurl.model.dto.UrlReponse;
import com.rafaelcavalcante.encurtadorurl.model.dto.UrlRequest;
import com.rafaelcavalcante.encurtadorurl.repository.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UrlService {

    private UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlReponse gerarUrlEncurtada(UrlRequest urlRequest, HttpServletRequest httpServletRequest) {
        String id;

        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (this.urlRepository.existsById(id));

        this.urlRepository.save(new Url(id, urlRequest.url(), LocalDateTime.now().plusMinutes(1)));

        var redirectUrl = httpServletRequest.getRequestURL().toString().replace("encurtador-url", id);

        return new UrlReponse(redirectUrl);
    }
}
