package smartup.academy.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import smartup.academy.dto.CourseDTO;
import smartup.academy.dto.InstructorDTO;
import smartup.academy.entity.Course;
import smartup.academy.entity.User;
import smartup.academy.serves.InstructorServis;
import smartup.academy.serves.UserServise;

import java.util.List;

/*
1. /api/instructors endpoint yarating
POST   - /api/instructors: Yangi instructor yaratish
GET    - /api/instructors: Hamma instructor'larni olish
GET    - /api/instructors/{instructorId}: instructor'ni id orqali olish
PUT    - /api/instructors: instructor'ni yangilash
DELETE - /api/instructors/{instructorId}: instructor'ni o'chirish
POST   - /api/instructors/{instructorId}/courses: Yangi course yaratish
GET    - /api/instructors/{instructorId}/courses: instructor'ning barcha kurslarini olish
*/
@Controller
@RequestMapping("/api/instructors")
public class InstructorController {

    InstructorServis instructorServis;
    UserServise  userServise;

    public InstructorController(InstructorServis instructorServis) {
        this.instructorServis = instructorServis;
    }

    @GetMapping
    public String instructorForm(Model model){
        List<InstructorDTO> instructorDTOs=instructorServis.instructorAll();
        model.addAttribute("instructorDTOs",instructorDTOs);
        return "/instructor/instructor-form.html";
    }

    @GetMapping("/Input")
    public String instructorPost(Model model){
        InstructorDTO instructorDTO=new InstructorDTO();
        model.addAttribute("instructorDTO",instructorDTO);
        return "/instructor/instructorInput.html";
    }

    @PostMapping("/input")
    public String instructorPost(@ModelAttribute InstructorDTO instructorDTO){
        instructorServis.instructorPersist(instructorDTO);
        return "redirect:/api/instructors";
    }

    @GetMapping("/merge/{id}")
    public String instructorMerge(@PathVariable int id,Model model){
       model.addAttribute("instructorDTO", instructorServis.instructorFindById(id));
        return "/instructor/instructor-merge.html";
    }

    @PostMapping("/Merge")
    public String instructorMerges(@ModelAttribute InstructorDTO instructorDTO){
        instructorServis.instructorMerge(instructorDTO);
        return  "redirect:/api/instructors";
    }

    @GetMapping("/setting")
    public String instructorSettings(Model model){
        InstructorDTO instructorDTO=new InstructorDTO();
        model.addAttribute(instructorDTO);

        return "/setting.html";
    }

    @GetMapping("/instructorCourse/{id}")
    public String instructorCourse(@PathVariable int id, Model model){
        model.addAttribute("id",id);
        model.addAttribute("courseDTOs",instructorServis.instructorAllCourse(id));
        return "/instructor/instructorAllCourse-form.html";
    }

    @GetMapping("/addCourse/{id}")
    public String addCourse(@PathVariable int id, Model model){
        CourseDTO courseDTO=new CourseDTO();
        courseDTO.setInstructorId(id);
        model.addAttribute("courseDTO",courseDTO);
        System.out.println(courseDTO);
        return "/instructor/instructor-addCourse.html";
    }

    @PostMapping("/AddCourse")
    public String AddCourse(@ModelAttribute CourseDTO courseDTO){
        instructorServis.instructorAddCourseSave(courseDTO);
        return "redirect:/api/instructors/instructorCourse/"+courseDTO.getInstructorId();
    }
    @GetMapping("/delete/{id}")
    public String instructorDelete(@PathVariable int id){
        instructorServis.instructorDeleteById(id);
        return "/api/instructors";
    }

    @GetMapping("/search")
    public String search(@RequestParam("search") String a,Model model){
       List<InstructorDTO> instructorDTOS= instructorServis.instructorSearch(a);
       model.addAttribute("instructorDTOs",instructorDTOS);
        return "/instructor/instructor-form.html";
    }

}
