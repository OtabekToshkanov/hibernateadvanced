//package uz.smartup.group2.AcademyProject.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class Config {
//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource) {
//        JdbcUserDetailsManager detailsManager = new JdbcUserDetailsManager(dataSource);
//
//        detailsManager.setUsersByUsernameQuery("SELECT email, password FROM user WHERE email = ?");
//        detailsManager.setAuthoritiesByUsernameQuery("SELECT email, role FROM role WHERE username = ?");
//
//        return detailsManager;
//    }
//
////    @Bean
////    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
////        UserDetails wayne = User.builder()
////                .username("wayne")
////                .password("{noop}secret")
////                .roles("STUDENT")
////                .build();
////
////        UserDetails martin = User.builder()
////                .username("martin")
////                .password("{noop}secret")
////                .roles("STUDENT", "INSTRUCTOR")
////                .build();
////
////        UserDetails melmen = User.builder()
////                .username("melmen")
////                .password("{noop}secret")
////                .roles("STUDENT", "INSTRUCTOR", "MANAGER")
////                .build();
////
////        UserDetails alex = User.builder()
////                .username("alex")
////                .password("{noop}secret")
////                .roles("STUDENT", "INSTRUCTOR", "MANAGER", "ADMIN")
////                .build();
////
////        return new InMemoryUserDetailsManager(wayne, martin, melmen, alex);
////    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(authRegistry ->
//                        authRegistry
//                                .requestMatchers(HttpMethod.GET, "/v1/instructor/**").hasAnyRole("ADMIN", "ROLE_INSTRUCTOR")
//                                .requestMatchers(HttpMethod.POST, "/v1/instructor/**").hasAnyRole("ADMIN", "ROLE_INSTRUCTOR")
//                                .requestMatchers(HttpMethod.PUT, "/v1/instructor/**").hasAnyRole("ADMIN", "ROLE_INSTRUCTOR")
//                                .requestMatchers(HttpMethod.DELETE, "/v1/instructor/**").hasRole("ROLE_INSTRUCTOR")
//                                .requestMatchers(HttpMethod.GET, "/v1/students/**").hasAnyRole("ADMIN", "ROLE_STUDENT")
//                                .requestMatchers(HttpMethod.POST, "/v1/students/**").hasAnyRole("ADMIN", "ROLE_STUDENT")
//                                .requestMatchers(HttpMethod.PUT, "/v1/students/**").hasAnyRole("ADMIN", "ROLE_STUDENT")
//                                .requestMatchers(HttpMethod.DELETE, "/v1/students/**").hasAnyRole("ADMIN", "ROLE_STUDENT")
//                                .requestMatchers("/access-denied").permitAll()
//                                .anyRequest().authenticated() )
//                .exceptionHandling(configurer ->
//                        configurer.accessDeniedPage("/access-denied"))
//                .formLogin(form ->
//                        form.loginPage("/v1/login")
//                                .loginProcessingUrl("/authenticate")
//                                .permitAll())
//                .logout(LogoutConfigurer::permitAll);
//
//        http.csrf(AbstractHttpConfigurer::disable);
//        http.httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }
//}




























package uz.smartup.group2.AcademyProject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Config {

    @Autowired
    private DataSource dataSource;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .anyRequest().authenticated()
                )
                .logout(LogoutConfigurer::permitAll);

        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    private String[] getRolesFromDatabase(String... roles) {
        List<String> rolesFromDatabase = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            for (String role : roles) {
                String query = "SELECT role FROM student WHERE role = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, role);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            rolesFromDatabase.add(role);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed");
        }
        return rolesFromDatabase.toArray(new String[0]);
    }
}
