package com.rafaelcavalcante.encurtadorurl.controller;

import com.rafaelcavalcante.encurtadorurl.model.dto.UrlReponse;
import com.rafaelcavalcante.encurtadorurl.model.dto.UrlRequest;
import com.rafaelcavalcante.encurtadorurl.repository.UrlRepository;
import com.rafaelcavalcante.encurtadorurl.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlController {

    private UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping(value = "/encurtador-url")
    public ResponseEntity<UrlReponse> encurtadorUrl(@RequestBody UrlRequest urlRequest,
                                                    HttpServletRequest httpServletRequest) {

        UrlReponse urlReponse = this.urlService.criarUrlEncurtada(urlRequest, httpServletRequest);

        return ResponseEntity.ok(urlReponse);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Void> redirecionar(@PathVariable("id") String id){
        return this.urlService.buscar(id);
    }
}
