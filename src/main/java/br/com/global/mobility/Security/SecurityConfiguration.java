package br.com.global.mobility.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        http.httpBasic()
            .and()
                .authorizeHttpRequests() 

                // Usuários
                // .antMatchers(HttpMethod.GET, "/api/usuario/**").authenticated()
                // .antMatchers(HttpMethod.POST, "/api/usuario/**").authenticated()
                // .antMatchers(HttpMethod.PUT, "/api/usuario/**").authenticated()
                // .antMatchers(HttpMethod.DELETE, "/api/usuario/**").hasRole("ADMIN")

                // web
                // .antMatchers(HttpMethod.GET, "/api/**").authenticated()
                // .antMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                // .antMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
                // .antMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                
                // Others
                // .anyRequest().denyAll()
                .anyRequest().permitAll()

            .and()
                .csrf().disable()
                .headers().frameOptions().disable()
            .and()
                .formLogin()
        ;        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
}
