package com.example.blogpostapi.webclient;

import com.example.blogpostapi.entity.Post;
import com.example.blogpostapi.response.PostResponse;
import com.example.blogpostapi.utils.HatchwaysHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HatchwaysBlogService {

    @Autowired
    WebClient webClient;

    public Mono<PostResponse> getPosts(String tag) {
        return webClient.get()
                .uri(HatchwaysHost.Blog.POSTS.getValue(), uri -> uri.queryParam("tag", tag).build())
                .retrieve()
                .bodyToMono(PostResponse.class);
    }
}
