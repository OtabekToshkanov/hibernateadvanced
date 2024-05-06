package smartup.academy.security;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class UserSecurity {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(14);
    }

//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
//        UserDetails Bahrom= UserDTO.builder()
//                .username("Bahrom")
//                .password("{noop}1234")
//                .roles("STUDENT")
//                .build();
//
//        UserDetails Axmad= UserDTO.builder()
//                .username("Axmad")
//                .password("{noop}1234")
//                .roles("INSTRUKTOR","MANAGER","ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(Bahrom,Axmad);
//    }
//      @Bean
//      public UserDetailsManager userDetailsManager(DataSource dataSource){
//          JdbcUserDetailsManager detailsManager=new JdbcUserDetailsManager(dataSource);
//
//          detailsManager.setUsersByUsernameQuery("SELECT username,password,enabled FORM user WHERE username=?");
//          detailsManager.setAuthoritiesByUsernameQuery("SELECT username,role FROM role WHERE username=?");
//
//          return detailsManager;
//      }
@Bean
public UserDetailsManager userDetailsManager(DataSource dataSource){
    JdbcUserDetailsManager detailsManager = new JdbcUserDetailsManager(dataSource);

    detailsManager.setUsersByUsernameQuery("SELECT username, password, enabled FROM user WHERE username = ?");
    detailsManager.setAuthoritiesByUsernameQuery("SELECT username, role FROM role WHERE username = ?");

    return detailsManager;
}
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.formLogin(form->
//                        form
//                                .loginPage("/login")
//                                .loginProcessingUrl("/save")
//                                .permitAll())
//                .exceptionHandling(configurer->
//                        configurer.accessDeniedPage("/accses-denied"))
////                .logout(logout->logout.permitAll()); tagidagi kod asli;
//                .logout(LogoutConfigurer::permitAll)
//                .authorizeHttpRequests(authRegistry ->
//                        authRegistry
//                                .requestMatchers(HttpMethod.GET, "/save").hasAnyRole("STUDENT")
//                                .requestMatchers(HttpMethod.GET, "/saveAl").hasAnyRole("INSTRUKTOR","STUDENT")
//                                .anyRequest()
//                                .authenticated()
//
//                );
//
//        http.cors(AbstractHttpConfigurer::disable);
//        http.httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authRegistry -> authRegistry
                    .requestMatchers(HttpMethod.GET, "/api/students","/api/students/*").hasAnyRole("STUDENT","INSTRUCTOR","ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/instructors","/api/instructors/*").hasAnyRole("ADMIN","INSTRUCTOR")
                    .requestMatchers("/accses-denied").permitAll()
                    .anyRequest().authenticated()
            )
            .exceptionHandling(configurer -> configurer
                    .accessDeniedPage("/accses-denied"))
            .formLogin(form -> form
                    .loginPage("/login")
                    .loginProcessingUrl("/kirishMumkin")
                    .permitAll())
            .logout(LogoutConfigurer::permitAll);



    http.cors(AbstractHttpConfigurer::disable);
    http.httpBasic(Customizer.withDefaults());

    return http.build();
}

}
