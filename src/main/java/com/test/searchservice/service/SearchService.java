package com.test.searchservice.service;

import com.test.searchservice.domain.Keyword;
import com.test.searchservice.repository.SearchRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SearchRepository searchRepository;

    @Transactional
    public void saveKeyword(String query){

        Keyword keyword = searchRepository.findByKeyword(query);
        Long keywordCnt;

        if(keyword != null) {
            keywordCnt = keyword.getCnt();
            keywordCnt += 1;
            keyword.setCnt(keywordCnt);
            searchRepository.save(keyword);
        }else{
            searchRepository.save(query);
        }

    }
}
