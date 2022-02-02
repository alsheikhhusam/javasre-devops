package com.example.mymedia.controllers;

import com.example.mymedia.dto.AllPostsDTO;
import com.example.mymedia.dto.CreatePostDto;
import com.example.mymedia.models.MmPost;
import com.example.mymedia.services.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    private PostService postService;

    private final Logger logger = LoggerFactory.getLogger(MmPostController.class);

    @Autowired
    public MmPostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllPostsDTO> getAllPosts() {
        logger.debug("Processing request to retrieve all posts");
        AllPostsDTO posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("{id}")
    public ResponseEntity<MmPost> getPostById(@PathVariable Integer id) {
        return ResponseEntity.ok(null);

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNewPost(@RequestBody CreatePostDto postDto) throws URISyntaxException {
        System.out.println(postDto.getContent());
        return ResponseEntity.created(new URI("http://localhost:" + port + "/api/posts/some-id")).build();
    }
}
