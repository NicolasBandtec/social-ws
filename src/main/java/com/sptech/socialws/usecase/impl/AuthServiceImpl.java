package com.sptech.socialws.usecase.impl;

import com.sptech.socialws.domain.Interest;
import com.sptech.socialws.domain.UserInterests;
import com.sptech.socialws.domain.Usuario;
import com.sptech.socialws.domain.dtos.UserDTO;
import com.sptech.socialws.domain.response.DefaultResponse;
import com.sptech.socialws.domain.response.LoginResponse;
import com.sptech.socialws.domain.exception.NoSessionException;
import com.sptech.socialws.domain.exception.NullValueException;
import com.sptech.socialws.domain.exception.UserAlreadyExistsException;
import com.sptech.socialws.domain.exception.UserNotFoundException;
import com.sptech.socialws.gateway.repository.InterestRepository;
import com.sptech.socialws.gateway.repository.UserInterestRepository;
import com.sptech.socialws.gateway.repository.UserRepository;
import com.sptech.socialws.usecase.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserInterestRepository userInterestRepository;
    private final InterestRepository interestRepository;

    @Autowired
    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            UserInterestRepository userInterestRepository,
            InterestRepository interestRepository
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userInterestRepository = userInterestRepository;
        this.interestRepository = interestRepository;
    }

    @Override
    public ResponseEntity<Usuario> newUser(UserDTO user){

        Usuario userModel = Usuario
                .Builder
                .create()
                .withEmail(user.getEmail().toLowerCase())
                .withFullname(user.getFullname())
                .withTelephone(user.getTelephone())
                .withPassword(passwordEncoder.encode(user.getPassword()))
                .withArtisticName(user.getArtisticName())
                .withIsArtist(user.getIsArtist())
                .withBirthdate(user.getBirthdate())
                .withCpf(user.getCpf())
                .withIsConnected(false)
                .build();

        if(userRepository.findByEmail(user.getEmail()).isPresent())
            throw new UserAlreadyExistsException(
                    "Este email já está associado à uma conta."
            );

        userRepository.save(userModel);
        saveInterests(user, userModel);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userModel);
    }

    @Override
    public ResponseEntity<LoginResponse> login(String email, String password) {
        UserDetails user = loadUserByUsername(email.toLowerCase());

        boolean passwordMatch =
                passwordEncoder.matches(password, user.getPassword());

        if(passwordMatch){

            Optional<Usuario> foundUser =
                    userRepository.findByEmail(email.toLowerCase());

            if(foundUser.isPresent()){

                foundUser.get().setConnected(true);
                userRepository.save(foundUser.get());
                Usuario responseUser = foundUser.get();

                UsernamePasswordAuthenticationToken auth = new
                        UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities());

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(auth);

                return ResponseEntity
                        .status(HttpStatus.FOUND).body(
                            LoginResponse.Builder
                            .create()
                            .withEmail(responseUser.getEmail())
                            .withCpf(responseUser.getCpf())
                            .withTelephone(responseUser.getTelephone())
                            .withFullname(responseUser.getFullname())
                            .withIsArtist(responseUser.getIsArtist())
                            .withUserId(responseUser.getUserId())
                            .withArtisticName(responseUser.getArtisticName())
                            .withBirthdate(responseUser.getBirthdate())
                            .withInterests(getInterests(responseUser))
                            .build()
                );
            }
        }

        throw new UserNotFoundException();
    }

    @Override
    public ResponseEntity<DefaultResponse> logout(int userId) {
        Optional<Usuario> user =
                userRepository.findByUserIdAndIsConnectedTrue(userId);

        if(user.isPresent()){
            user.get().setConnected(false);
            SecurityContextHolder.clearContext();
            userRepository.save(user.get());

            return ResponseEntity.status(HttpStatus.OK).body(
                    DefaultResponse.Builder
                            .create()
                            .withMessage("Sessão finalizada com sucesso.")
                            .withCode("SESSION_FINISHED")
                            .build()
            );

        } else {
            throw new NoSessionException();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> foundUser = userRepository.findByEmail(username);

        if(foundUser.isEmpty()){
            throw new UserNotFoundException();
        }

        Usuario user = foundUser.get();

        String roles = (user.getIsArtist()) ? "ARTIST" : "USER";

        return User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

    private void saveInterests(UserDTO user, Usuario userModel){

        for(Integer interest: user.getInterests()){

            Interest inter = interestRepository
                    .findById(interest)
                    .orElseThrow(
                            () -> new NullValueException("Nenhum interesse encontrado")
                    );

            userInterestRepository.save(
                    UserInterests.Builder
                            .create()
                            .withInterest(inter)
                            .withUsuario(userModel)
                            .build()
            );
        }
    }

    private List<String> getInterests(Usuario user){
        List<String> interests = new ArrayList<>();

        for(UserInterests interest : userInterestRepository.findByUsuario(user)){
            interests.add(interest.getInterest());
        }
        return interests;
    }

}
