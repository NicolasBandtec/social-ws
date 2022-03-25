package com.sptech.socialws.usecase.impl;

import com.sptech.socialws.domain.Post;
import com.sptech.socialws.domain.Usuario;
import com.sptech.socialws.domain.dtos.PostDTO;
import com.sptech.socialws.domain.response.DefaultResponse;
import com.sptech.socialws.domain.response.PostResponse;
import com.sptech.socialws.domain.exception.PostNotFoundException;
import com.sptech.socialws.domain.exception.UserNotFoundException;
import com.sptech.socialws.gateway.repository.PostRepository;
import com.sptech.socialws.gateway.repository.UserRepository;
import com.sptech.socialws.usecase.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@SuppressWarnings("unused")
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<DefaultResponse> createPost(PostDTO post) {
        Integer idUser = post.getIdUser();

        Usuario user = userRepository
                .findById(idUser)
                .orElseThrow(
                        UserNotFoundException::new
                );

        Post newPost = Post
                .Builder
                .create()
                .withUser(user)
                .withDescricao(post.getDescricao())
                .withDataHora()
                .build();

        repository.save(newPost);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        DefaultResponse.Builder
                                .create()
                                .withMessage("Publicação criada")
                                .withCode("POST_CREATED")
                                .build()
                );
    }

    @Override
    public List<PostResponse> findAll() {

        List<PostResponse> response = new ArrayList<>();
        List<Post> foundPosts = repository.findAll();

        for (Post post : foundPosts) {
            PostResponse foundPost =
                    PostResponse
                            .Builder
                            .create()
                            .withIdPost(post.getIdPost())
                            .withDataHora(post.getDataHora())
                            .withDescricao(post.getDescricao())
                            .withUserFullName(post.getUser().getFullname())
                            .withUserId(post.getUser().getUserId())
                            .build();

            response.add(foundPost);
        }
        return response;
    }

    @Override
    public ResponseEntity<String> deletePost(Integer id) {

        Post recoveredPost = repository
                .findById(id)
                .orElseThrow(
                        PostNotFoundException::new
                );

        repository.delete(recoveredPost);

        return ResponseEntity
                .ok()
                .build();
    }

    @Override
    public ResponseEntity<String> updatePost(Integer id, PostDTO post) {

        Post recoveredPost = repository
                .findById(id)
                .orElseThrow(
                        PostNotFoundException::new
                );

        recoveredPost.setDescricao(post.getDescricao());

        repository.save(recoveredPost);

        return ResponseEntity
                .ok()
                .build();
    }

    @Override
    public Optional<Post> findPostById(Integer id) {

        return Optional.ofNullable(
                repository
                        .findById(id)
                        .orElseThrow(
                                PostNotFoundException::new
                        )
        );
    }
}
