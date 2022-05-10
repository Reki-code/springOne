package me.rekii.tacocloud.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.log4j.Log4j2;
import me.rekii.tacocloud.User;
import me.rekii.tacocloud.data.UserRepository;

@Log4j2
@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig {

    @Bean
    public CommandLineRunner userLoader(PasswordEncoder passwordEncoder, UserRepository userRepo) {
        return args -> {
            User user = new User("togashi", passwordEncoder.encode("togashi"), "fullname", "street", "city", "state",
                    "zip", "phoneNumber");
            userRepo.save(user);
        };
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null) {
                return user;
            }
            throw new UsernameNotFoundException("user [" + username + "] not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers("/design", "/orders").access("hasRole('USER')")
                .antMatchers(HttpMethod.POST, "/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/", "/**").access("permitAll")
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                // Make H2-Console non-secured; for debug purposes
                .and()
                .csrf().disable()
                // Allow pages to be loaded in frames from the same origin; needed for
                // H2-Console
                .headers()
                .frameOptions()
                .sameOrigin()
                .and()
                .build();
    }
}
