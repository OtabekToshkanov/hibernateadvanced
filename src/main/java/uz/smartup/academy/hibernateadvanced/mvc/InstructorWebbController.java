package uz.smartup.academy.hibernateadvanced.mvc;


import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uz.smartup.academy.hibernateadvanced.dto.CourseDTO;
import uz.smartup.academy.hibernateadvanced.dto.InstructorDTO;
import uz.smartup.academy.hibernateadvanced.dto.ReviewDTO;
import uz.smartup.academy.hibernateadvanced.entity.Role;
import uz.smartup.academy.hibernateadvanced.entity.User;
import uz.smartup.academy.hibernateadvanced.service.CourseService;
import uz.smartup.academy.hibernateadvanced.service.InstructorService;
import uz.smartup.academy.hibernateadvanced.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/web/instructors")
public class InstructorWebbController {
    private final InstructorService service;
    private final CourseService courseService;


    public InstructorWebbController(InstructorService service, CourseService courseService, UserService userService) {
        this.service = service;
        this.courseService = courseService;
    }

    private UserDetails getLoggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof UserDetails)
            return (UserDetails) principal;

        return null;
    }

    @GetMapping
    public String listInstructors(Model model) {
        List<InstructorDTO> instructors = service.getInstructors();

        model.addAttribute( "instructors", instructors);

        return "getInstructors";
    }

    @RequestMapping("/{id}")
    public String deleteInstructor(@PathVariable int id) {
        service.deleteInstructor(id);
        return "redirect:/web/instructors";
    }

    @GetMapping("/createInstructor")
    public String createInstructors(Model model) {
        InstructorDTO instructor = new InstructorDTO();

        model.addAttribute("instructor", instructor);

        return "createInstructor";
    }
    @PostMapping("/saveInstructor")
    public String saveInstructor(@ModelAttribute("instructor") InstructorDTO instructorDTO, BindingResult bindingResult, Model model) {
        service.createInstructor(instructorDTO);
        return "redirect:/web/instructors";
    }



    @GetMapping("/{id}")
    public String getInstructorById(@PathVariable int id, Model model) {


        model.addAttribute("instructor", service.getInstructor(id));
        model.addAttribute("courses", service.getCourses(id));

        return "getInstructor";
    }



    @GetMapping("/{id}/courses")
    public String addCourse(Model model) {
        model.addAttribute("course", new CourseDTO());

        return "instructorCourseForm";
    }

    @PostMapping("/{instructorId}/courses/save")
    public String saveCourse(@PathVariable("instructorId") int id, @ModelAttribute CourseDTO course, RedirectAttributes redirectAttributes) {
        service.addCourse(id, course);

        redirectAttributes.addAttribute("id", id);

        return "redirect:/web/instructors/{id}";
    }

    @GetMapping("/{id}/updateForm")
    public String updateForm(Model model, @PathVariable int id) {
        model.addAttribute("instructor", service.getInstructor(id));

        return "updateInstructor";
    }


    @RequestMapping("/{id}/update")
    public String updateMapping(@PathVariable int id, @ModelAttribute("instructor") InstructorDTO instructor ) {
//        System.out.println(instructor);
        service.updateInstructor(instructor);

        return "redirect:/web/instructors";
    }

    @RequestMapping("/{id}/courses/{courseId}/deleteCourses")
    public String deleteCourseFromInstructor(@PathVariable("courseId") int courseId, @PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        courseService.deleteCourseById(courseId);

        redirectAttributes.addAttribute("id", id);

        return "redirect:/web/instructors/{id}";
    }


}

