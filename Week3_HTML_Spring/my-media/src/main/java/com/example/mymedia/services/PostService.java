package com.example.mymedia.services;

import com.example.mymedia.dao.MmPostRepository;
import com.example.mymedia.dto.AllPostsDTO;
import com.example.mymedia.dto.PostDTO;
import com.example.mymedia.models.MmPost;
import com.example.mymedia.models.MmTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private MmPostRepository postRepository;

    @Autowired
    public void setPostRepository(MmPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public AllPostsDTO getAllPosts() {
        List<MmPost> allPosts = postRepository.findAll();

        List<PostDTO> transformed =  allPosts.stream()
                .map(p -> new PostDTO(p.getId(), p.getContent(), p.getPoster().getUsername(), p.getReceiver().getUsername(),
                        p.getTags()
                                .stream()
                                .map(MmTag::getName)
                                .collect(Collectors.toSet())
                ))
                .collect(Collectors.toList());

        return new AllPostsDTO(transformed);
    }

}
