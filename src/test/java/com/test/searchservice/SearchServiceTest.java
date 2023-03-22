package com.test.searchservice;

import com.test.searchservice.domain.Keyword;
import com.test.searchservice.repository.SearchRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class SearchServiceTest {

    @Autowired
    private WebClient kakaoWebClient;

    @Autowired
    private SearchRepository searchRepository;

    @AfterEach
    public void clean() {
        searchRepository.deleteAll();
    }

    @Test
    void callKakaoApiTest() {

        String query = "맛집";
        Integer page = 1;
        String sort = "accuracy";
        Integer size = 10;

        ResponseEntity<String> result = kakaoWebClient.get()
                .uri(builder -> builder.path("/v2/search/blog").queryParam("query", query)
                        .queryParam("page", page)
                        .queryParam("sort", sort)
                        .queryParam("size", size).build())
                .retrieve()
                .toEntity(String.class)
                .block();

        Assertions.assertEquals(HttpStatusCode.valueOf(200), result.getStatusCode());

    }

    @Test
    public void saveKeywordTest() throws Exception {

        String query = "맛집";

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

        List<Keyword> keywordList = searchRepository.findAll();
        Keyword testKeyword = keywordList.get(0);

        Assertions.assertEquals("맛집", testKeyword.getKeyword());
    }

    @Test
    public void getPopularKeywordTest() throws Exception {

        Keyword keyword = new Keyword();
        keyword.setKeyword("꽃집");
        keyword.setCnt(Long.valueOf(1));
        searchRepository.save(keyword);

        Optional<List<Keyword>> keywordList = searchRepository.findFirst10ByOrderByCntDesc();
        Keyword testKeyword = keywordList.get().get(0);

        Assertions.assertEquals("꽃집", testKeyword.getKeyword());
    }

}
