package com.example.mymedia.controllers;

import com.example.mymedia.dto.CreatePostDto;
import com.example.mymedia.models.MmPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("posts")
public class MmPostController {
    @Value("${server.port}")
    int port;

    @GetMapping
    public ResponseEntity<List<MmPost>> getAllPosts() {
        List<MmPost> posts = new ArrayList<>();
        posts.addAll(Arrays.asList(new MmPost(1, "august.duet", "john.doe", "Hello")));
        return ResponseEntity.ok(posts);
    }

    @GetMapping("{id}")
    public ResponseEntity<MmPost> getPostById(@PathVariable Integer id) {
        return ResponseEntity.ok(new MmPost(id, "august.duet", "john.doe", "Hello"));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNewPost(@RequestBody CreatePostDto postDto) throws URISyntaxException {
        System.out.println(postDto.getContent());
        return ResponseEntity.created(new URI("http://localhost:" + port + "/api/posts/some-id")).build();
    }
}
