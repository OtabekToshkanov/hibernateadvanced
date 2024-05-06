package uz.smartup.academy.hibernateadvanced.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SpringSecurity {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager detailsManager = new JdbcUserDetailsManager(dataSource);

        detailsManager.setUsersByUsernameQuery("SELECT username, password, enabled FROM user WHERE username = ?");
        detailsManager.setAuthoritiesByUsernameQuery("SELECT username, role FROM role WHERE username = ?");

        return detailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authRegistry ->
                        authRegistry
                                .requestMatchers(HttpMethod.GET,"/web/instructors").hasAnyRole("ADMIN","MANAGER")
                                .requestMatchers(HttpMethod.POST,"/web/instructors/*").hasAnyRole("ADMIN","MANAGER")
                                .requestMatchers(HttpMethod.PUT,"/web/instructors/*").hasAnyRole("ADMIN","MANAGER")
                                .requestMatchers(HttpMethod.DELETE,"/web/instructors/*").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET,"/web/students").hasAnyRole("ADMIN","MANAGER","INSTRUCTOR")
                                .requestMatchers(HttpMethod.POST,"/web/students/*").hasAnyRole("ADMIN","MANAGER","INSTRUCTOR")
                                .requestMatchers(HttpMethod.PUT,"/web/students/*").hasAnyRole("ADMIN","MANAGER","INSTRUCTOR")
                                .requestMatchers(HttpMethod.DELETE,"/web/students/*").hasAnyRole("ADMIN","MANAGER")

                                .requestMatchers(HttpMethod.GET,"/web/courses").hasAnyRole("ADMIN","MANAGER","INSTRUCTOR","STUDENT")
                                .requestMatchers(HttpMethod.PUT,"/web/courses/*").hasRole("INSTRUCTOR")
                                .requestMatchers(HttpMethod.DELETE,"/web/courses/*").hasAnyRole("INSTRUCTORS")
                                .anyRequest()
                                .authenticated())
                .formLogin(form ->
                        form.loginPage("/login")
                                .loginProcessingUrl("/authenticate")
                                .permitAll())
                .exceptionHandling(configurer ->  configurer.accessDeniedPage("/access-denied"))
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }



}