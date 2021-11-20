package com.example.blogpostapi.service.impl;

import com.example.blogpostapi.entity.Post;
import com.example.blogpostapi.entity.Tuple;
import com.example.blogpostapi.exception.BlogPostApiException;
import com.example.blogpostapi.response.PostResponse;
import com.example.blogpostapi.service.PostService;
import com.example.blogpostapi.utils.HatchwaysEnums;
import com.example.blogpostapi.utils.impl.GenericErrorEnum;
import com.example.blogpostapi.utils.StrUtil;
import com.example.blogpostapi.webclient.HatchwaysBlogService;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostServiceImpl.class);

    @Autowired
    HatchwaysBlogService hatchwaysBlogService;

    private final String SORT_BY_DEFAULT = HatchwaysEnums.SortBy.ID.getValue();
    private final String DIRECTION_DEFAULT = HatchwaysEnums.Order.ASC.getValue();

    @Override
    public PostResponse findAll(String tags, String sortBy, String direction) {

        if (Strings.isNullOrEmpty(tags)) throw new BlogPostApiException(GenericErrorEnum.ER0001);
        if (!StrUtil.sortByIsValid(sortBy)) throw new BlogPostApiException(GenericErrorEnum.ER0002);
        if (!StrUtil.directionIsValid(direction)) throw new BlogPostApiException(GenericErrorEnum.ER0003);

        String[] tagList = StrUtil.getSubStringsSeparatedByComma(tags);
        if (Strings.isNullOrEmpty(sortBy)) sortBy = SORT_BY_DEFAULT;
        if (Strings.isNullOrEmpty(direction)) direction = DIRECTION_DEFAULT;

        Flux<Post> postFlux = Flux.just();
        for (String tag : tagList) {
            postFlux = Flux.merge(hatchwaysBlogService.getPosts(tag).map(PostResponse::getPosts).flatMapMany(Flux::fromIterable), postFlux);
        }

        int length = tagList.length;
        List<Post> posts = postFlux.collectList().block();

        PostResponse response = new PostResponse();
        try {
            HashMap<Integer, Tuple<Integer, Post>> postHashMapTuple = new HashMap<>();
            posts.forEach(p -> {
                Tuple<Integer, Post> tuple = postHashMapTuple.get(p.getId());
                if (tuple != null && tuple.getK() != null)
                    postHashMapTuple.put(p.getId(), new Tuple<>(tuple.getK() + 1, tuple.getV()));
                else postHashMapTuple.put(p.getId(), new Tuple<>(1, p));
            });

            postHashMapTuple.forEach((k, t) -> {
                if (t.getK() == length) response.getPosts().add(t.getV());
            });
            sortList(response.getPosts(), sortBy, direction);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

        LOGGER.info("hello world - FOR TEST PURPOSE");
        return response;
    }

    private void sortList(List<Post> list, final String sortBy, final String direction) {
        list.sort((p1, p2) -> {
            switch (sortBy) {
                case "id":
                    if (direction.equals(HatchwaysEnums.Order.ASC.getValue())) return p1.getId().compareTo(p2.getId());
                    if (direction.equals(HatchwaysEnums.Order.DESC.getValue())) return p2.getId().compareTo(p1.getId());
                case "likes":
                    if (direction.equals(HatchwaysEnums.Order.ASC.getValue())) return p1.getLikes().compareTo(p2.getLikes());
                    if (direction.equals(HatchwaysEnums.Order.DESC.getValue())) return p2.getLikes().compareTo(p1.getLikes());
                case "popularity":
                    if (direction.equals(HatchwaysEnums.Order.ASC.getValue())) return p1.getPopularity().compareTo(p2.getPopularity());
                    if (direction.equals(HatchwaysEnums.Order.DESC.getValue())) return p2.getPopularity().compareTo(p1.getPopularity());
                case "reads":
                    if (direction.equals(HatchwaysEnums.Order.ASC.getValue())) return p1.getReads().compareTo(p2.getReads());
                    if (direction.equals(HatchwaysEnums.Order.DESC.getValue())) return p2.getReads().compareTo(p1.getReads());
                default:
                    return p1.getId().compareTo(p2.getId());
            }
        });
    }

}
