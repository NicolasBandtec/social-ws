package com.sptech.socialws.gateway.repository;

import com.sptech.socialws.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findAll();
}
