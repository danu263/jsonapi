package com.example.blogpostapi.webclient;

import com.example.blogpostapi.response.PostResponse;
import com.example.blogpostapi.utils.HatchwaysHostUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class HatchwaysBlogService {

    @Autowired
    WebClient webClient;

    public Mono<PostResponse> getPosts(String tag) {
        return webClient.get()
                .uri(HatchwaysHostUrl.Blog.POSTS.getValue(), uri -> uri.queryParam("tag", tag).build())
                .retrieve()
                .bodyToMono(PostResponse.class);
    }
}
