package com.test.searchservice.repository;

import com.test.searchservice.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<Keyword, Long> {
    Keyword findByKeyword(String keyword);

    List<Keyword> findFirst10ByOrderByCntDesc();
}
