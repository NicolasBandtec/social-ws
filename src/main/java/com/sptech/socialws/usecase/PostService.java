package com.sptech.socialws.usecase;

import com.sptech.socialws.domain.Post;
import com.sptech.socialws.domain.dtos.PostDTO;
import com.sptech.socialws.domain.response.DefaultResponse;
import com.sptech.socialws.domain.response.PostResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PostService {

    ResponseEntity<DefaultResponse> createPost(PostDTO post);

    List<PostResponse> findAll();

    ResponseEntity<String> deletePost(Integer id);

    ResponseEntity<String> updatePost(Integer id, PostDTO post);

    Optional<Post> findPostById(Integer id);

}
