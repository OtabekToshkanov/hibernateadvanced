package uz.smartup.academy.hibernateadvanced;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uz.smartup.academy.hibernateadvanced.entity.*;
import uz.smartup.academy.hibernateadvanced.service.UserService;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class HibernateadvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateadvancedApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(UserService userService) {
        return args -> {
            User user = new User();
            user.setId(6);
            user.setUsername("otabek");
            user.setPassword("1234");
            user.setEnabled("Y");
            user.setFirstName("Otabek");
            user.setLastName("Toshkanov");
            user.setEmail("otabek@mail");

            Set<Role> roles = new HashSet<>();
            Role role = new Role();
            role.setRole("ADMIN");
            roles.add(role);
            Role role2 = new Role();
            role2.setRole("STUDENT");
            roles.add(role2);

            userService.registerUser(user, roles);
        };
    }

//    @Bean
//    public CommandLineRunner runner(AppDAO dao) {
//        return runner -> {
////            InstructorDetail detail = new InstructorDetail();
////            detail.setYoutubeChannel("allambalo");
////            detail.setHobby("instructing");
////
////            Instructor instructor = new Instructor();
////            instructor.setName("Otabek");
////            instructor.setEmail("otabek@email");
////            instructor.setInstructorDetail(detail);
////
////            dao.save(instructor);
////
//////            dao.deleteById(instructor.getId());
////
////            Instructor instructor = dao.findInstructorById(4);
////
////            instructor.setCourses(dao.findCoursesByInstructorId(instructor.getId()));
////
////            System.out.println(instructor);
//
//            Course course = new Course();
//            course.setTitle("Backend");
//            course.setInstructor(dao.findInstructorByIdJoinFetch(4));
//
//            Student student = new Student();
//            student.setFirstName("Otabek");
//            student.setLastName("Toshkanov");
//            student.setEmail("wayne@mail");
//
//            course.addStudent(student);
//
//            Student student2 = new Student();
//            student2.setFirstName("Wayne");
//            student2.setLastName("Bruce");
//            student2.setEmail("bruce@mail");
//
//            course.addStudent(student2);
//
//            dao.update(course);
//
//            System.out.println(course);
//        };
//    }
}
