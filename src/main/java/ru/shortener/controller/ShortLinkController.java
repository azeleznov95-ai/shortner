package ru.shortener.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shortener.dto.ShortLinkResponse;
import ru.shortener.dto.UrlRequest;
import ru.shortener.model.ShortLink;
import ru.shortener.service.ShortLinkService;
import ru.shortener.config.AppProperties;
@RestController
public class ShortLinkController {
    private final ShortLinkService service;
    private final AppProperties appProperties;
    ShortLinkController(AppProperties appProperties, ShortLinkService service){
        this.service = service;
        this.appProperties = appProperties;

        
    }

    
    @PostMapping("/shorten")
    public ShortLinkResponse createShortLink(@RequestBody UrlRequest urlRequest){
        String url = urlRequest.getUrl();
        ShortLink shortLink = service.createShortLink(url);
        String baseUrl = appProperties.getBaseurl();
        String clientUrl = baseUrl+"/"+shortLink.getShortcode();
        ShortLinkResponse clientUrlResponse = new ShortLinkResponse(clientUrl);
        return clientUrlResponse;
    }
    @GetMapping("{code}")
    public ResponseEntity<Void> getShortLink(@PathVariable String code){
        String originalUrl = service.getOriginalUrl(code);
        if (originalUrl == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(301).header("Location", originalUrl).build();


    }


}
