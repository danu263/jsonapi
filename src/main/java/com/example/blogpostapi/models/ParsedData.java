package com.example.blogpostapi.models;

import lombok.Data;

import java.util.List;

@Data
public class ParsedData {
    private String uuid;
    private List<String> names;
}
