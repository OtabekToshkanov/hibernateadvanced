package uz.smartup.academy.hibernateadvanced.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.smartup.academy.hibernateadvanced.dto.CourseDTO;
import uz.smartup.academy.hibernateadvanced.dto.InstructorDTO;
import uz.smartup.academy.hibernateadvanced.service.InstructorServis;
import uz.smartup.academy.hibernateadvanced.service.UserServise;

import java.util.List;

@Controller
@RequestMapping("/web/instructors/")
public class InstructorController {

    InstructorServis instructorServis;
    UserServise userServise;

    public InstructorController(InstructorServis instructorServis) {
        this.instructorServis = instructorServis;
    }

    @GetMapping("")
    public String instructorForm(Model model){
        List<InstructorDTO> instructorDTOs=instructorServis.instructorAll();
        model.addAttribute("instructorDTO",instructorDTOs);
        return "instructor/instructor-form.html";
    }

//    @GetMapping("/Input")
//    public String instructorPost(Model model){
//        InstructorDTO instructorDTO=new InstructorDTO();
//        model.addAttribute("instructorDTO",instructorDTO);
//        return "instructor/instructorInput.html";
//    }
//
//    @PostMapping("/input")
//    public String instructorPost(@ModelAttribute InstructorDTO instructorDTO){
//        instructorServis.instructorPersist(instructorDTO);
//        return "redirect:/api/instructors";
//    }

    int ins_id;
    @GetMapping("edit/{id}")
    public String instructorEdit(@PathVariable int id,Model model){
        ins_id=id;
       model.addAttribute("instructorDTO", instructorServis.instructorFindById(id));
        return "/instructor/instructor-edit.html";
    }

    @PostMapping("edit")
    public String instructorEdit(@ModelAttribute InstructorDTO instructorDTO){
        instructorDTO.setId(ins_id);
        //System.out.println(instructorDTO);
        instructorServis.instructorMerge(instructorDTO);
        return  "redirect:/web/instructors/";
    }


    @GetMapping("{id}/courses")
    public String instructorCourse(@PathVariable int id, Model model){
        ins_id = id;
        InstructorDTO instructorDTO = instructorServis.instructorFindById(ins_id);
        model.addAttribute("instructorDTO",instructorDTO);
        model.addAttribute("courseDTO",instructorServis.instructorAllCourse(id));
        return "instructor/instructor-course-form.html";
    }

    @GetMapping("{id}/courses/new")
    public String addCourse(Model model){
        CourseDTO courseDTO=new CourseDTO();
        courseDTO.setInstructorId(ins_id);
        InstructorDTO instructorDTO = instructorServis.instructorFindById(ins_id);
        model.addAttribute("instructorDTO",instructorDTO);
        model.addAttribute("courseDTO",courseDTO);
        return "course/course-create.html";
    }

    @PostMapping("courses")
    public String AddCourse(@ModelAttribute CourseDTO courseDTO){
        courseDTO.setInstructorId(ins_id);
        instructorServis.instructorAddCourseSave(courseDTO);
        return "redirect:/web/instructors/"+courseDTO.getInstructorId()+"/courses";
    }
    @GetMapping("/delete/{id}")
    public String instructorDelete(@PathVariable int id){
        instructorServis.instructorDeleteById(id);
        return "redirect:/web/instructors/";
    }


    @GetMapping("firstName")
    public String getInstructorFirstname(@RequestParam(name = "search") String firstName, Model model){
        model.addAttribute("instructorDTO", instructorServis.findInstructorByFirstName(firstName));
        return "instructor/instructor-form.html";
    }

//    @GetMapping("{id}/courses/courseTitle")
//    public String getInstructorCourseTitle(@RequestParam(name = "search") String firstName, Model model){
//        model.addAttribute("courseDTO", instructorServis.findInstructorCourseBuTitle(ins_id, firstName));
//        return "instructor/instructor-course-form.html";
//    }



}
