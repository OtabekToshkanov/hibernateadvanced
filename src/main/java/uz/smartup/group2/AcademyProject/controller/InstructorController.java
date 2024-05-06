package uz.smartup.group2.AcademyProject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uz.smartup.group2.AcademyProject.dao.CourseDAO;
import uz.smartup.group2.AcademyProject.dto.*;
import uz.smartup.group2.AcademyProject.entity.Course;
import uz.smartup.group2.AcademyProject.entity.Instructor;
import uz.smartup.group2.AcademyProject.entity.Review;
import uz.smartup.group2.AcademyProject.repository.CourseRepository;
import uz.smartup.group2.AcademyProject.repository.InstructorRepository;
import uz.smartup.group2.AcademyProject.service.CourseService;
import uz.smartup.group2.AcademyProject.service.InstructorService;
import uz.smartup.group2.AcademyProject.service.StudentService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Controller
public class InstructorController extends HttpServlet {

    private final InstructorService instructorService;
    private final InstructorDTOUtil instructorDTOUtil;
    private final CourseService courseService;
    private final CourseDTOUtil courseDTOUtil;

    public InstructorController(InstructorService instructorService, InstructorDTOUtil instructorDTOUtil, CourseService courseService, CourseDTOUtil courseDTOUtil, CourseRepository courseRepository) {
        this.instructorService = instructorService;
        this.instructorDTOUtil = instructorDTOUtil;
        this.courseService = courseService;
        this.courseDTOUtil = courseDTOUtil;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/v1/instructor/signup")
    public String instructorRegister(Model model, InstructorDTO instructorDTO) {
        model.addAttribute("instructor", instructorDTO);
        return "instructorRegister";
    }

    @PostMapping("/v1/instructor/signup")
    public String register(@Valid @ModelAttribute("instructor") InstructorDTO instructorDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "instructorRegister";
        } else {

            String encodedPassword = BCrypt.hashpw(instructorDTO.getPassword(), BCrypt.gensalt(12));
            instructorDTO.setPassword(encodedPassword);
            instructorDTO.setRole("INSTRUCTOR");
            instructorService.save(instructorDTO);
            Instructor instructor = instructorService.findByEmail(instructorDTO.getEmail());
            return "redirect:/v1/instructor/" + instructor.getId();
        }
    }

    @RequestMapping(value = "/v1/instructor/{id}", method = RequestMethod.GET)
    public String instructorForm(@PathVariable("id") int id, Model model) {
        InstructorDTO instructorDTO = instructorService.findById(id);
        model.addAttribute("instructorId", instructorDTO.getId());
        model.addAttribute("instructorFirstName", instructorDTO.getFirstName());
        model.addAttribute("instructorLastName", instructorDTO.getLastName());
        model.addAttribute("instructorEmail", instructorDTO.getEmail());
        return "instructorProfile";
    }

    @GetMapping("/v1/instructor/settings/{id}")
    public String settingForm(@PathVariable("id") int id,
                              Model model) {
        InstructorDTO instructorDTO = instructorService.findById(id);
        model.addAttribute("instructorDTOProfile", instructorDTO);
        model.addAttribute("instructorUpdateId", id);
        return "instructorProfileSettings";
    }

    @RequestMapping(value = "/v1/instructor/update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") int id, Model model) {
        InstructorDTO instructorDTO = instructorService.findById(id);
        model.addAttribute("instructorUserId", id);
        model.addAttribute("updateInstructorFirstName", instructorDTO.getFirstName());
        model.addAttribute("updateInstructorLastName", instructorDTO.getLastName());
        model.addAttribute("updateInstructorEmail", instructorDTO.getEmail());
        return "instructorUpdateProfile";
    }

    @PostMapping("/v1/instructor/update/{id}")
    public String updateForm(@ModelAttribute("userId") InstructorDTO instructorDTO,
                             @PathVariable("id") int id) {
        instructorService.update(instructorDTO);
        return "redirect:/v1/instructor/" + id;
    }

    @RequestMapping(value = "/v1/instructor/{id}/courses", method = RequestMethod.GET)
    public String courses(@PathVariable("id") int id, Model model) {
        InstructorDTO instructorDTO = instructorService.findById(id);
        model.addAttribute("instructorIdCourse", instructorDTO.getId());
        model.addAttribute("instructorName", instructorDTO.getFirstName());
        model.addAttribute("courses", instructorService.getCourses(id));
        return "instructorCourses";
    }

    @GetMapping("/v1/instructor/{id}/courses/new")
    public String instructorAddForm(Model model, @PathVariable("id") int id) {
        InstructorDTO instructorDTO = instructorService.findById(id);
        model.addAttribute("mxId", instructorDTO.getId());
        return "instructorAddCourse";
    }

    @PostMapping("/v1/instructor/{id}/courses/new")
    public String InstructorCourseForm(@PathVariable("id") int id,
                                       @ModelAttribute("courseDTO") CourseDTO courseDTO,
                                       RedirectAttributes redirectAttributes, Model model) {
        InstructorDTO instructorDTO = instructorService.findById(id);
        instructorService.addCourse(instructorDTO.getId(), courseDTO);
        model.addAttribute("courseNew", courseDTO);
        redirectAttributes.addAttribute("id", instructorDTO.getId());
        return "redirect:/v1/instructor/{id}/courses";
    }

    @GetMapping("/v1/instructor/{id}/logout")
    public String logOut(@PathVariable("id") int id) {
        instructorService.deleteById(id);
        return "redirect:/home";
    }

    @RequestMapping( value = "/v1/instructor/{instructorId}/delete/course/{courseId}", method = RequestMethod.GET)
    public String deleteCourse(
                               @PathVariable("courseId") int courseId,
                               @PathVariable("instructorId") int instructorId,
                               RedirectAttributes redirectAttributes, Model model) {
        courseService.deleteCourseById(courseId);
        redirectAttributes.addAttribute("instructorDeleteId", instructorId);
        return "redirect:/v1/instructor/{instructorDeleteId}/courses";
    }
    @GetMapping("/v1/instructor/{instructorId}/updateCourse/{courseId}")
    public String updateCourseForm(@PathVariable("courseId") int courseId,
                                   @PathVariable("instructorId") int instructorId,
                                   Model model){
        Course course = courseService.getById(courseId);
        model.addAttribute("updateCourseId", course.getId());
        model.addAttribute("updateCourse", course);
        return "instructorUpdateCourses";
    }

    @PostMapping("/v1/instructor/{instructorId}/updateCourse/{courseId}")
    public String updateCourse(@PathVariable("instructorId") int instructorId,
                               @PathVariable("courseId") int courseId,
                               @RequestParam("courseName") String courseName,
                               @RequestParam("decription") String description,
                               @RequestParam("duration") String duration,
                               @RequestParam("courseFee") int courseFee,
                               @RequestParam("courseFormat") String courseFormat,
                               @RequestParam("category") String category){

        courseService.update(courseId,courseName, description, duration, courseFee, courseFormat, category );
        return "redirect:/v1/instructor/"+instructorId+"/courses";
    }
    @GetMapping("/v1/instructor/{instructorId}/reviews/{courseId}")
    public String reviewForm(@PathVariable("courseId") int courseId,
                             @PathVariable("instructorId") int instructorId,
                             Model model){
        model.addAttribute("allReviews", instructorService.getAllCourseReviews(courseId));
        return "instructorCourseReviews";
    }
}
