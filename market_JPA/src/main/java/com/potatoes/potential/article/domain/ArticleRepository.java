package com.potatoes.potential.article.domain;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, UUID> {

    Optional<Article> findBySeq(Long articleSeq);
}
