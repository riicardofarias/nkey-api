package br.com.nkey.api.controller;

import br.com.nkey.api.model.User;
import br.com.nkey.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Método de autenticação de usuários
     * @param user User
     * @return LoginResponse
     */
    @PostMapping(value = {"/login", "login"})
    public ResponseEntity<?> login(@RequestBody User user) {
        return new ResponseEntity<>(userService.login(user), HttpStatus.OK);
    }

    /**
     * Registra um novo usuário
     * @param user User
     * @return User
     */
    @PostMapping(value = {"/register", "register"})
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.registerNewUser(user), HttpStatus.OK);
    }
}
