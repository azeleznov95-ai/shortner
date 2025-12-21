package ru.shortener.service;

import ru.shortener.dto.ShortLinkResponse;
import ru.shortener.dto.ShortLinkStatsResponse;
import ru.shortener.model.ShortLink;

public interface ShortLinkService {
     ShortLink createShortLink(String url);
     String getOriginalUrl(String shortCode);
     ShortLinkStatsResponse getStats(String shortCode);
}

