package uz.smartup.academy.hibernateadvanced.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uz.smartup.academy.hibernateadvanced.dto.CourseDTO;
import uz.smartup.academy.hibernateadvanced.dto.InstructorDTO;
import uz.smartup.academy.hibernateadvanced.dto.StudentDTO;
import uz.smartup.academy.hibernateadvanced.service.InstructorService;

import java.util.List;

@Controller
@RequestMapping("/web/instructors")
public class InstructorWebController {
    private InstructorService service;

    public InstructorWebController(InstructorService service) {
        this.service = service;
    }

    @GetMapping
    public String getInstructors(Model model) {
        model.addAttribute("instructors", service.getInstructors());
        return "instructor-list";
    }

    @GetMapping("/instructorForm")
    public String saveStudent(Model model) {
        model.addAttribute("instructor", new InstructorDTO());
        return "save-instructor";
    }

    @PostMapping("/saveInstructor")
    public String createInstructor(@ModelAttribute("instructor") InstructorDTO instructorDTO) {
        service.createInstructor(instructorDTO);
        //, RedirectAttributes attributes
        //attributes.addAttribute("id", id);

        return "redirect:/web/instructors";
    }

    @RequestMapping("/{id}/delete")
    public String deleteInstructors(@PathVariable("id") int id) {
        service.deleteInstructor(id);
        return "redirect:/web/instructors";
    }

    @PostMapping("{id}/updateInstructors")
    public String updateInstructors(@ModelAttribute("instructor") InstructorDTO instructorDTO) {
        service.updateInstructor(instructorDTO);
        return "redirect:/web/instructors";
    }

    @PostMapping("/{id}/updateForm")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("instructor", service.getInstructor(id));
        return "update-instructors";
    }

    @GetMapping("/{id}")
    public String info(@PathVariable("id") int id, Model model) {

        model.addAttribute("courses", service.getCourses(id));
        model.addAttribute("instructor", service.getInstructor(id));
        return "instructors-courses";
    }

    @GetMapping("/{instructorId}/courseForm")
    public String courseForm(Model model){
        model.addAttribute("course",new CourseDTO());
        return "save-course";
    }
    @PostMapping("/{instructorId}/saveCourse")
    public String createCourse(@ModelAttribute("course") CourseDTO courseDTO,@PathVariable("instructorId") int id,RedirectAttributes attributes) {
        service.addCourse(id,courseDTO);
        attributes.addAttribute("id",id);


        return "redirect:/web/instructors/{id}";
    }


}
