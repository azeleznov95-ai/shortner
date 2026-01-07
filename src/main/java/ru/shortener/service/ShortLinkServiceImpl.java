package ru.shortener.service;

import jakarta.transaction.Transactional;


import org.springframework.stereotype.Service;

import ru.shortener.dto.ShortLinkStatsResponse;
import ru.shortener.exceptons.InvalidUrlException;
import ru.shortener.exceptons.ShortLinkNotFoundException;
import ru.shortener.model.ShortLink;
import ru.shortener.repository.ShortLinkRepository;
import ru.shortener.util.Encoder;


import java.net.URI;
import java.net.URISyntaxException;

import java.util.Optional;


@Service
public class ShortLinkServiceImpl implements ShortLinkService {
    private final ShortLinkRepository repository;
    private final Encoder encoder;
    ShortLinkServiceImpl(ShortLinkRepository repository, Encoder base62Encoder){
        this.repository = repository;
        this.encoder= base62Encoder;
    }

    @Override
    public ShortLink createShortLink(String url) {

        try {
            URI uri = new URI(url);

            if (!(uri.getScheme().equals("http")|| uri.getScheme().equals("https"))|| (uri.getHost()==null)) {
                throw new InvalidUrlException("Invalid URL");
            }


        }catch (URISyntaxException e){
            throw new InvalidUrlException("Invalid URL");
        }
        Optional<ShortLink> optionalShortLink=repository.findByOriginalUrl(url);
        if (optionalShortLink.isEmpty()){
            ShortLink shortLink = new ShortLink();
            shortLink.setOriginalUrl(url);


            String shortcode = encoder.encode();
            shortLink.setShortcode(shortcode);

            return repository.save(shortLink);
        }
        return optionalShortLink.get();
    }


    @Transactional
    public String getOriginalUrl(String shortCode) {
        Optional<ShortLink> shortLink = repository.findByShortcode(shortCode);

        if (shortLink.isEmpty()){
            throw new ShortLinkNotFoundException("Not Found");
        }
        else{
            shortLink.get().incCount();
            return shortLink.get().getOriginalUrl();
        }



    }


    public ShortLinkStatsResponse getStats(String shortCode){
        Optional<ShortLink> shortLink = repository.findByShortcode(shortCode);
        ShortLinkStatsResponse response = new ShortLinkStatsResponse();

        if (shortLink.isPresent()) {
            response.setCount(shortLink.get().getCount());

            response.setShortcode(shortLink.get().getShortcode());
            response.setOriginalUrl(shortLink.get().getOriginalUrl());
            response.setCreatedAt(shortLink.get().getCreatedAt());
            return  response;
        }
        throw new ShortLinkNotFoundException("Not found");
    }
}
