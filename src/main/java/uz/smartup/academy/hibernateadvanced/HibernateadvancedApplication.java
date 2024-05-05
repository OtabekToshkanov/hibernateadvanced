package uz.smartup.academy.hibernateadvanced;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uz.smartup.academy.hibernateadvanced.dao.AppDAO;
import uz.smartup.academy.hibernateadvanced.entity.Course;
import uz.smartup.academy.hibernateadvanced.entity.Instructor;
import uz.smartup.academy.hibernateadvanced.entity.Student;

@SpringBootApplication
public class HibernateadvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateadvancedApplication.class, args);
    }

}
