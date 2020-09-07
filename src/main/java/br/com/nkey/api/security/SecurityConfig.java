package br.com.nkey.api.security;

import br.com.nkey.api.service.UserService;
import br.com.nkey.api.util.JwtTokenUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    public SecurityConfig(UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/users/register", "/users/login");
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
            .antMatchers("/**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
        .addFilter(new AuthorizationFilter(authenticationManager(), userService, jwtTokenUtil));
    }
}
