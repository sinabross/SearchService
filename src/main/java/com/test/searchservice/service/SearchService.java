package com.test.searchservice.service;

import com.test.searchservice.domain.Keyword;
import com.test.searchservice.exception.exception.KeywordListNotFoundException;
import com.test.searchservice.exception.exception.QueryNotValidException;
import com.test.searchservice.repository.SearchRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    SearchRepository searchRepository;

    @Transactional
    public void saveKeyword(String query){

        if("".equals(query) || query == null) {
            throw new QueryNotValidException("Query Not Valid");
        }

        Keyword keyword = searchRepository.findByKeyword(query);
        Long keywordCnt;

        if(keyword != null) {
            keywordCnt = keyword.getCnt();
            keywordCnt += 1;
            keyword.setCnt(keywordCnt);
            searchRepository.save(keyword);
        }else{
            Keyword word = new Keyword();
            word.setKeyword(query);
            word.setCnt(Long.valueOf(1));

            searchRepository.save(word);
        }

    }

    public List<Keyword> getPopularKeyword(){
        List<Keyword> keywordList = searchRepository.findFirst10ByOrderByCntDesc()
                                                    .orElseThrow(() -> new KeywordListNotFoundException("KeywordList Not Found"));
        return keywordList;
    }

}
