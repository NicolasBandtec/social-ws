package com.sptech.socialws.rest.controller;

import com.sptech.socialws.domain.Post;
import com.sptech.socialws.domain.dtos.PostDTO;
import com.sptech.socialws.domain.response.DefaultResponse;
import com.sptech.socialws.domain.response.PostResponse;
import com.sptech.socialws.usecase.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@SuppressWarnings("unused")
public class PostController {

    @Autowired
    private PostService service;

    @GetMapping("/find-all")
    public List<PostResponse> findAll(){
        return service.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<DefaultResponse> createPost(@RequestBody PostDTO post){
        return service.createPost(post);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Integer id){
        return service.deletePost(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePost(
            @PathVariable Integer id,
            @RequestBody PostDTO post
    ){
        return service.updatePost(id, post);
    }

    @GetMapping("/get-post/{id}")
    public Optional<Post> findPostById(Integer id){
        return service.findPostById(id);
    }
}
