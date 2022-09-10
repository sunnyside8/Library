package com.example.librarywithspring2.config;

import com.example.librarywithspring2.repository.ClientRepository;
import com.example.librarywithspring2.service.ClientDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.
//                csrf().disable()
//                // define which requests are allowed and which not
//                .authorizeRequests().
//                requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
//                // everyone can login and register
//                        antMatchers("/", "/login", "/register").permitAll().
//                // all other pages are available for logger in users
//                        anyRequest().
//                authenticated().
//                and().
//                formLogin()
//                .loginPage("/login")
//                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
//                // the name of the password form field
//                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
//                .defaultSuccessUrl("/")
//                .failureForwardUrl("/login-error")
//                .permitAll()
//                .defaultSuccessUrl("/")
//                .and()
//                .logout().invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/authentication/logout-success").deleteCookies("JSESSIONID").permitAll();
//
//
//        return http.build();
//    }

    @Bean
    public ClientDetailsService clientDetailsService(ClientRepository clientRepository) {
        return new ClientDetailsService(clientRepository);
    }

    @Bean
    public SecurityFilterChain filterChainTwo(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/login", "/register").permitAll()
                .antMatchers("/clients/**").hasRole("USER")
                .antMatchers("/order/**").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/authors/**").hasRole("USER")
                .antMatchers("/authors/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/books/**").hasRole("USER")
                .antMatchers("/books/**").hasRole("ADMIN")
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }


}



