package br.com.nkey.api.service;

import br.com.nkey.api.dto.LoginResponse;
import br.com.nkey.api.exception.BadCredentialException;
import br.com.nkey.api.model.User;
import br.com.nkey.api.repository.UserRepository;
import br.com.nkey.api.util.JwtTokenUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;
    private final JwtTokenUtil jwt;

    public UserService(UserRepository repository, BCryptPasswordEncoder encoder, JwtTokenUtil jwt) {
        this.repository = repository;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public Optional<User> findById(String id){
        return repository.findById(id);
    }

    public LoginResponse login(User userDto){
        User user = repository.findByEmail(userDto.getEmail()).orElseThrow(() ->
            new BadCredentialException("Usuário não encontrado")
        );

        if(!encoder.matches(userDto.getPassword(), user.getPassword())){
            throw new BadCredentialException("Senha inválida");
        }

        String token = jwt.generateToken(user.getId());

        return new LoginResponse(user, token);
    }

    public User registerNewUser(User user){
        repository.findByEmail(user.getEmail()).ifPresent(e -> {
            throw new RuntimeException("Já existe um usuário com esse email");
        });

        user.setPassword(encoder.encode(user.getPassword()));

        return repository.save(user);
    }
}
