package uz.smartup.academy.hibernateadvanced;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.smartup.academy.hibernateadvanced.dao.AppDAO;
import uz.smartup.academy.hibernateadvanced.entity.Course;
import uz.smartup.academy.hibernateadvanced.entity.Instructor;
import uz.smartup.academy.hibernateadvanced.entity.Student;
import uz.smartup.academy.hibernateadvanced.entity.User;
import uz.smartup.academy.hibernateadvanced.repository.UserRepository;

import java.util.List;

@SpringBootApplication
public class HibernateadvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateadvancedApplication.class, args);
    }


//    @Bean
//    public ApplicationRunner applicationRunner(UserRepository repository, PasswordEncoder passwordEncoder) {
//        return args -> {
//            List<User> users = repository.findAll();
//
//            users.forEach(user ->{
//                user.setPassword(passwordEncoder.encode("12345"));
//            });
//
//            repository.saveAll(users);
//        };
//    }
}
