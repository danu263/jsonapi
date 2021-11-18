package com.example.blogpostapi.service;

import com.example.blogpostapi.response.PostResponse;

public interface PostService {

    PostResponse findAll(String tags, String sortBy, String direction);
}
