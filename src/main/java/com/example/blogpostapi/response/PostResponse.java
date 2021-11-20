package com.example.blogpostapi.response;

import com.example.blogpostapi.entity.Post;

import java.util.ArrayList;
import java.util.List;

public class PostResponse {

    private List<Post> posts = new ArrayList<>();

    public PostResponse(List<Post> posts) {
        this.posts = posts;
    }

    public PostResponse() {
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
