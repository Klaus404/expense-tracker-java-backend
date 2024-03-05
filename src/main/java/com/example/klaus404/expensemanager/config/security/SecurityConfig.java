package com.example.klaus404.expensemanager.config.security;

import com.example.klaus404.expensemanager.config.security.CustomAuthentificationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    private final CustomAuthentificationProvider customAuthentificationProvider;

    public SecurityConfig(CustomAuthentificationProvider customAuthentificationProvider) {
        this.customAuthentificationProvider = customAuthentificationProvider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(
                        authorizeConfig -> {
                            authorizeConfig.requestMatchers("/login").permitAll();
                            authorizeConfig.requestMatchers("/error").hasRole("ADMIN");
                            authorizeConfig.requestMatchers("/users").hasRole("ADMIN");
                            authorizeConfig.requestMatchers("/products").authenticated();
                            authorizeConfig.anyRequest().authenticated();
                        }
                )
                .authenticationProvider(customAuthentificationProvider)
                .formLogin(withDefaults())
                .oauth2Login(withDefaults());

        var user = User.withUsername("user")
                .password("password")
                .roles("ADMIN")
                .authorities("ROLE_ADMIN")
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
