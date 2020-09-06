package br.com.nkey.api.security;

import br.com.nkey.api.model.User;
import br.com.nkey.api.service.UserService;
import br.com.nkey.api.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AuthorizationFilter extends BasicAuthenticationFilter {
    final UserService userService;
    final JwtTokenUtil jwtTokenUtil;

    final List<GrantedAuthority> freeRoutes = Collections.singletonList((GrantedAuthority) () -> "/events");

    public AuthorizationFilter(AuthenticationManager authenticationManager, UserService userService, JwtTokenUtil jwtTokenUtil) {
        super(authenticationManager);
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("authorization");

        if(token == null){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid security token");
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token){
        Claims claims = jwtTokenUtil.parseToken(token);

        User user = userService.findById(claims.getSubject()).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)
        );

        return new UsernamePasswordAuthenticationToken(user, null, freeRoutes);
    }
}
