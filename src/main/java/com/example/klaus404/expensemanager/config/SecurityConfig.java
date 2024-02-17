package com.example.klaus404.expensemanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomAuthentificationProvider customAuthentificationProvider;

    public SecurityConfig(CustomAuthentificationProvider customAuthentificationProvider) {
        this.customAuthentificationProvider = customAuthentificationProvider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(
                        authorizeHttp -> {
                            authorizeHttp.requestMatchers("/login").permitAll();
                            authorizeHttp.requestMatchers("/error").permitAll();
                            authorizeHttp.requestMatchers("/products").hasRole("ADMIN");
                            authorizeHttp.anyRequest().authenticated();
                        }
                )
                .authenticationProvider(customAuthentificationProvider)
                .formLogin(withDefaults())
                .oauth2Login(withDefaults());

        var user = User.withUsername("user")
                .password("password")
                .roles("ADMIN")
                .build();

        var userDetailsService = new InMemoryUserDetailsManager(user);

        http.userDetailsService(userDetailsService);


        return http.build();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
