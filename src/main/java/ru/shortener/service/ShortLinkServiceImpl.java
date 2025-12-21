package ru.shortener.service;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import ru.shortener.dto.ShortLinkStatsResponse;
import ru.shortener.model.ShortLink;
import ru.shortener.repository.ShortLinkRepository;
import ru.shortener.util.Base62Encoder;


import java.net.URI;
import java.net.URISyntaxException;



@Service
public class ShortLinkServiceImpl implements ShortLinkService {
    private final ShortLinkRepository repository;
    private final Base62Encoder base62Encoder;
    ShortLinkServiceImpl(ShortLinkRepository repository,Base62Encoder base62Encoder){
        this.repository = repository;
       this.base62Encoder= base62Encoder;
    }

    @Override
    public ShortLink createShortLink(String url) {

        try {
            URI uri = new URI(url);

            if (!(uri.getScheme().equals("http")|| uri.getScheme().equals("https"))|| (uri.getHost()==null)) {
                throw new IllegalArgumentException("Invalid URL");
            }

        }catch (URISyntaxException e){
            throw new IllegalArgumentException("Invalid URL");
        }




        ShortLink shortLink = new ShortLink();
        shortLink.setOriginalUrl(url);
        repository.save(shortLink);
        Long id =shortLink.getId();

        String shortcode = base62Encoder.encode(id);
        shortLink.setShortcode(shortcode);

        return repository.save(shortLink);
    }


    @Transactional
    public String getOriginalUrl(String shortCode) {
        ShortLink shortLink = repository.findByShortcode(shortCode);

        if (shortLink != null) {
            shortLink.incCount();
            repository.save(shortLink);
            return shortLink.getOriginalUrl();
        }



        return null;
    }


    public ShortLinkStatsResponse getStats(String shortCode){
        ShortLink shortLink = repository.findByShortcode(shortCode);
        ShortLinkStatsResponse response = new ShortLinkStatsResponse();

        if (shortLink != null) {
            response.setCount(shortLink.getCount());

            response.setShortcode(shortLink.getShortcode());
            response.setOriginalUrl(shortLink.getOriginalUrl());
            response.setCreatedAt(shortLink.getCreatedAt());
            return  response;
        }
        return  null;
    }
}
