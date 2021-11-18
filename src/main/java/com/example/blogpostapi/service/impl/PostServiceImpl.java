package com.example.blogpostapi.service.impl;

import com.example.blogpostapi.entity.Post;
import com.example.blogpostapi.exception.BlogPostApiException;
import com.example.blogpostapi.response.PostResponse;
import com.example.blogpostapi.service.PostService;
import com.example.blogpostapi.utils.impl.GenericErrorEnum;
import com.example.blogpostapi.utils.StrUtil;
import com.example.blogpostapi.webclient.HatchwaysBlogService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    HatchwaysBlogService hatchwaysBlogService;

    private final String SORT_BY_DEFAULT = "id";
    private final String DIRECTION_DEFAULT = "asc";

    @Override
    public PostResponse findAll(String tags, String sortBy, String direction) {

        if (Strings.isNullOrEmpty(tags)) throw new BlogPostApiException(GenericErrorEnum.ER0001);
        if (!StrUtil.sortByIsValid(sortBy)) throw new BlogPostApiException(GenericErrorEnum.ER0002);
        if (!StrUtil.directionIsValid(direction)) throw new BlogPostApiException(GenericErrorEnum.ER0003);

        String[] tagList = StrUtil.getSubStringsSeparatedByComma(tags);
        if (Strings.isNullOrEmpty(sortBy)) sortBy = SORT_BY_DEFAULT;
        if (Strings.isNullOrEmpty(direction)) direction = DIRECTION_DEFAULT;

        List<Post> posts = new ArrayList<>();
        Flux<Post> postFlux = Flux.just();
        for (String tag : tagList) {
            postFlux = Flux.merge(hatchwaysBlogService.getPosts(tag).map(PostResponse::getPosts).flatMapMany(Flux::fromIterable), postFlux);
        }
        posts = postFlux.collectList().block();

        posts.forEach(p -> System.out.println(p.getId()));
        return new PostResponse(posts);
    }

}
