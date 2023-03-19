package com.test.searchservice.repository;

import com.test.searchservice.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<Keyword, Long> {
    Keyword findByKeyword(String keyword);

    void save(String keyword);
}
