package com.blog.post.blogapi.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configurable
public class SecurityConfig {


//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails userDetails= User.withDefaultPasswordEncoder()
//                .username("samar")
//                .password("samar")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(userDetails);
//
//    }
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.csrf(cstf->cstf.disable())
                    .authorizeHttpRequests((autz)->
                autz.anyRequest().authenticated()
            ).httpBasic(Customizer.withDefaults());

            return httpSecurity.build();
    }

}
