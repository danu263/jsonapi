package com.example.blogpostapi.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Serializable {

    private String author;
    private Integer authorId;
    private Integer id;
    private Integer likes;
    private Double popularity;
    private Integer reads;
    private List<String> tags;
    private Ping ping;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(author, post.author) && Objects.equals(authorId, post.authorId) && Objects.equals(id, post.id) && Objects.equals(likes, post.likes) && Objects.equals(popularity, post.popularity) && Objects.equals(reads, post.reads) && Objects.equals(tags, post.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, authorId, id, likes, popularity, reads, tags);
    }

    @Override
    public String toString() {
        return "Post{" +
                "author='" + author + '\'' +
                ", authorId=" + authorId +
                ", id=" + id +
                ", likes=" + likes +
                ", popularity=" + popularity +
                ", reads=" + reads +
                ", tags=" + tags +
                '}';
    }
}
