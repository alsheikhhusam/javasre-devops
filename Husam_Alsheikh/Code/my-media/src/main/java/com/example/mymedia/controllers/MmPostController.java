package com.example.mymedia.controllers;

import com.example.mymedia.dto.CreatePostDTO;
import com.example.mymedia.models.MmPost;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("posts")
public class MmPostController {
    @Value("${server.port}")
    int port;

    @GetMapping
    public ResponseEntity<List<MmPost>> getAllPosts(){

        //List<MmPost> posts = new ArrayList<>(Collections.singletonList(new MmPost(1, "husam", "john.doe", "hello")));

        return ResponseEntity.ok(null);
    }

    @GetMapping("{id}")
    public ResponseEntity<MmPost> getPostById(@PathVariable Integer id){
        //return ResponseEntity.ok(new MmPost(id, "husam", "john.doe", "hello"));
        return ResponseEntity.ok(null);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNewPost(@RequestBody CreatePostDTO createPostDTO) throws URISyntaxException {
        System.out.println(createPostDTO.getContent());
        return ResponseEntity.created(new URI("http:/localhost:" + port + "/api/posts/some-id")).build();
    }
}
