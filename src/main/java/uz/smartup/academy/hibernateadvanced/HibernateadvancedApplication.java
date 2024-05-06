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
