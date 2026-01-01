    package ru.shortener.repository;

    import org.springframework.data.jpa.repository.JpaRepository;
    import ru.shortener.model.ShortLink;

    import java.util.Optional;

    public interface ShortLinkRepository extends JpaRepository<ShortLink, Long> {
        Optional<ShortLink> findByShortcode(String shortcode);
        Optional<ShortLink> findByOriginalUrl(String originalUrl);
    }
