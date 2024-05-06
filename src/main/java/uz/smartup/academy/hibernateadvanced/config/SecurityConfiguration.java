package uz.smartup.academy.hibernateadvanced.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails wayne = User.builder()
                .username("wayne")
                .password("{noop}secret")
                .roles("STUDENT")
                .build();
        UserDetails martin = User.builder()
                .username("martin")
                .password("{noop}secret")
                .roles("INSTRUCTOR","STUDENT")
                .build();
        UserDetails melmen = User.builder()
                .username("melmen")
                .password("{noop}secret")
                .roles("INSTRUCTOR","STUDENT","MANAGER")
                .build();
        UserDetails alex = User.builder()
                .username("alex")
                .password("{noop}secret")
                .roles("INSTRUCTOR","STUDENT","MANAGER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(wayne,martin,melmen,alex);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
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
//                                .requestMatchers(HttpMethod.GET,"/web/reviews/*").hasAnyRole("ADMIN","MANAGER","INSTRUCTOR","STUDENT")
//                                .requestMatchers(HttpMethod.DELETE,"/web/reviews/*").hasAnyRole("STUDENT")


                                .anyRequest().authenticated())
        .formLogin(form ->
                form.loginPage("/login")
                        .loginProcessingUrl("/authenticate")
                        .permitAll())
                .exceptionHandling(configurer ->  configurer.accessDeniedPage("/access-denied"))
                .logout(LogoutConfigurer::permitAll);

        return  http.build();
    }
}
