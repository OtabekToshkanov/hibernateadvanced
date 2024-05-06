package smartup.academy.controller;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import smartup.academy.dto.*;
import smartup.academy.entity.Review;
import smartup.academy.entity.Student;
import smartup.academy.entity.User;
import smartup.academy.serves.CourseServer;
import smartup.academy.serves.InstructorServis;
import smartup.academy.serves.StudentServise;
import smartup.academy.serves.UserServise;

import java.util.List;


@Controller
@RequestMapping("/api/courses")
public class CourseController {
    UserServise userServise;
    CourseServer courseServer;
    StudentServise studentServise;
    InstructorServis instructorServis;

    public CourseController(UserServise userServise, CourseServer courseServer, StudentServise studentServise, InstructorServis instructorServis) {
        this.userServise = userServise;
        this.courseServer = courseServer;
        this.studentServise = studentServise;
        this.instructorServis = instructorServis;
    }

    @GetMapping
    public String reviewController(Model model) {
        int idd = 0;
        UserDetails details =userServise.getLoggedUser();
        UserDTO user=userServise.userFindByUsername(details.getUsername());
        if(details.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_STUDENT"))) {
            StudentDTO student=studentServise.studentFindByUserId(user.getId());
            idd=student.getId();
            model.addAttribute("courseDTO", studentServise.studentAllCourse(idd));
        }
        else
        if(details.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_INSTRUCTOR"))) {
          InstructorDTO instructorDTO=instructorServis.instructorFindByUserId(user.getId());
            idd=instructorDTO.getId();
            model.addAttribute("courseDTO", instructorServis.instructorAllCourse(idd));
        }
        else {
            model.addAttribute("courseDTO", courseServer.courseAll());
        }
            model.addAttribute("id",idd);
        return "course/course-form";
    }

    @GetMapping("/merge/{id}")
    public String courseMerge(@PathVariable int id,Model model){
        System.out.println(id);
        CourseDTO courseDTO=courseServer.courseFindByid(id);
        model.addAttribute("courseDTO",courseDTO);
        return "/course/courseMerge-form.html";
    }

    @PostMapping("/Marge")
    public String courseMergeS(@ModelAttribute CourseDTO courseDTO){
        courseServer.courseMerge(courseDTO);
        return "redirect:/api/instructors/instructorCourse/"+courseDTO.getInstructorId();
    }
    @GetMapping("/courseAllStudent/{id}")
    public String courseAllStudent(@PathVariable int id,Model model){
        List<StudentDTO> studentDTOS=courseServer.courseAllStudent(id);
        model.addAttribute("students",studentDTOS);
        return "/course/courseAllStudent-form.html";
    }

    @GetMapping("/courseAllReview/{id}")
    public String courseAllReview(@PathVariable int id,Model model){
        List<ReviewDTO> reviewDTOS=courseServer.courseAllReview(id);
        model.addAttribute("reviewDTOs",reviewDTOS);
        return "review/review-form.html";
    }
    @GetMapping("/delete/{id}/{instructorId}")
    public String deleteCourse(@PathVariable int id,@PathVariable int instructorId){
        courseServer.courseDelete(id);
        return "redirect:/api/instructors/instructorCourse/"+instructorId;
    }

}
