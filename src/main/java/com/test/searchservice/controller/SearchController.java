package com.test.searchservice.controller;

import com.test.searchservice.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/search/")
@RequiredArgsConstructor
public class SearchController {
    private final WebClient kakaoWebClient;

    @Autowired
    private SearchService searchService;

    @GetMapping("/blog")
    public Mono<String> searchBlog(@RequestParam @Nullable String query, @RequestParam @Nullable Integer page,
                                   @RequestParam @Nullable String sort, @RequestParam @Nullable Integer size) {

        Mono<String> mono = kakaoWebClient.get()
                .uri(builder -> builder.path("/v2/search/blog").queryParam("query", query)
                        .queryParam("page", page)
                        .queryParam("sort", sort)
                        .queryParam("size", size).build())
                .exchangeToMono(response -> {
                    return response.bodyToMono(String.class);
                });

        searchService.saveKeyword(query);

        return mono;
    }

}
