package br.com.nkey.api.service;

import br.com.nkey.api.model.User;
import br.com.nkey.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    public User save(User user){
        return repository.save(user);
    }
}
