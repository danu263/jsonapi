package com.example.blogpostapi.controller;

import com.example.blogpostapi.entity.Ping;
import com.example.blogpostapi.response.PostResponse;
import com.example.blogpostapi.service.PostService;
import com.example.blogpostapi.service.SQSConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class PostController {

    @Autowired
    PostService orderService;
    @Autowired
    SQSConfigService sqsConfigService;

    @GetMapping(path = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ping> ping() {
        return new ResponseEntity<>(new Ping(true), OK);
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostResponse> findAll(@RequestParam String tags,
                                                @RequestParam(required = false) String sortBy,
                                                @RequestParam(required = false) String direction
    ) {
        return new ResponseEntity<>(orderService.findAll(tags, sortBy, direction), OK);
    }

    @GetMapping(path = "/createQueue", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createSQS() {
        return new ResponseEntity<>(sqsConfigService.createQueue(), OK);
    }
}
