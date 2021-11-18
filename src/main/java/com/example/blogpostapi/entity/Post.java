package com.example.blogpostapi.entity;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Post implements Serializable {

    private String author;
    private Integer authorId;
    private Integer id;
    private Integer likes;
    private Double popularity;
    private Integer reads;
    private List<String> tags;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getReads() {
        return reads;
    }

    public void setReads(Integer reads) {
        this.reads = reads;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
