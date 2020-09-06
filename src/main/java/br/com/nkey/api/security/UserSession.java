package br.com.nkey.api.security;

import br.com.nkey.api.exception.BadCredentialException;
import br.com.nkey.api.model.User;
import br.com.nkey.api.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserSession {
    private final UserService userService;

    public UserSession(UserService userService) {
        this.userService = userService;
    }

    public User getLoggedUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        return userService.findById(user.getId()).orElseThrow(() ->
            new BadCredentialException("Credenciais inválidas")
        );
    }
}
