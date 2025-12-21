package ru.shortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shortener.model.ShortLink;

public interface ShortLinkRepository extends JpaRepository<ShortLink, Long> {
    ShortLink findByShortcode(String shortCode);
}
