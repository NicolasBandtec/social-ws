package com.sptech.socialws.gateway.repository;

import com.sptech.socialws.domain.UserInterests;
import com.sptech.socialws.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInterestRepository extends JpaRepository<UserInterests, Integer> {

    List<UserInterests> findByUsuario(Usuario usuario);

}
