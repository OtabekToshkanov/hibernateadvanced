package smartup.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import smartup.academy.dto.CourseDTO;
import smartup.academy.dto.InstructorDTO;
import smartup.academy.dto.ReviewDTO;
import smartup.academy.dto.StudentDTO;
import smartup.academy.entity.Student;
import smartup.academy.serves.ReviewServis;
import smartup.academy.serves.StudentServise;

import java.util.List;
/*
2. /api/students endpoint yarating
POST   - /api/students: Yangi student yaratish
GET    - /api/students: Hamma student'larni olish
GET    - /api/students/{studentId}: student'ni id orqali olish
PUT    - /api/students: student'ni yangilash
DELETE - /api/students/{studentId}: student'ni o'chirish
POST   - /api/students/{studentId}/courses/{courseId}: studentni kursga yozilish
GET    - /api/students/{studentId}/courses: studentning barcha kurslarini olish
DELETE - /api/students/{studentId}/courses/{courseId}: studentning kursini o'chirish
POST   - /api/students/{studentId}/courses/{courseId}/reviews: studentning kursiga sharh qo'shish
GET    - /api/students/{studentId}/courses/{courseId}/reviews: studentning kursining barcha sharhlarini olish
PUT    - /api/students/{studentId}/courses/{courseId}/reviews: studentning kursining sharhini yangilash
*/
@Controller
@RequestMapping("api/students")
public class StudentController {

    StudentServise studentServise;
    ReviewServis  reviewServis;

    public StudentController(StudentServise studentServise,ReviewServis  reviewServis) {
        this.studentServise = studentServise;
        this.reviewServis=reviewServis;
    }

    @GetMapping
    public String studentForm(Model model){
        List<StudentDTO> students=studentServise.studentAll();
        model.addAttribute("students",students);
        return "/students/student-form.html";
    }

    @GetMapping("/Input")
    public String instructorPost(Model model){
        System.out.println("  return instructor/instructorInput.html");
        InstructorDTO instructorDTO=new InstructorDTO();
        model.addAttribute("instructorDTO",instructorDTO);
        return "/students/student-form.html";
    }

    @GetMapping("/merge/{id}")
    public String studentMerge(@PathVariable int id,Model model){
       StudentDTO studentDTO= studentServise.studentFindById(id);
        model.addAttribute("studentDTO",studentDTO);
        return "/students/student-merge.html";
    }


    @PostMapping("/Merge")
    public String studentMerge(@ModelAttribute StudentDTO studentDTO){
        System.out.println(studentDTO);
        studentServise.studentMerge(studentDTO);
        return "redirect:/api/students";
    }

    @GetMapping("/{id}/course")
    public String studentAllCourse(@PathVariable int id,Model model){
        List<CourseDTO> courseDTOS=studentServise.studentAllCourse(id);
        model.addAttribute("courseDTO",courseDTOS);
        model.addAttribute("id",id);
        return "/students/studentAllCourse-form.html";
    }

    @GetMapping("/{studentId}/addCourse")
    public String studentAddCourse(@PathVariable int studentId,Model model){
        model.addAttribute("id",studentId);
        model.addAttribute("courseDTO",studentServise.filterAllCourse(studentId));
        return "/students/studentAddCourse-form.html";
    }

    @GetMapping("/{studentId}/addCourse/{courseId}")
    public String studentAddCourse(@PathVariable int studentId,@PathVariable int courseId,Model model){
        studentServise.studentAddCourse(studentId,courseId);
        return "redirect:/api/courses";
    }

    @GetMapping("/{studentId}/AllReview/{courseId}")
    public String studentAllReviev(@PathVariable int studentId,@PathVariable int courseId,Model model){
        List<ReviewDTO> reviewDTOS=studentServise.studentAllReview(studentId,courseId);
//        System.out.println(studentId);
        System.out.println(reviewDTOS);
        model.addAttribute("studentId",studentId);
        model.addAttribute("courseId",courseId);
        model.addAttribute("reviewDTOs",reviewDTOS);
        return "/review/review-form2.html";
    }

    @GetMapping("/{studentId}/AddReview/{courseId}")
    public String studentAddReview(@PathVariable int studentId,@PathVariable int courseId,Model model){
        ReviewDTO reviewDTO=new ReviewDTO();
        reviewDTO.setStudent_id(studentId);
        reviewDTO.setCourse_id(courseId);
        model.addAttribute("reviewDTO",reviewDTO);
        return "/students/studentAddReview-form.html";
    }

    @PostMapping("/addReview")
    public String studentAddReviewS(@ModelAttribute ReviewDTO reviewDTO){
        studentServise.studentAddReview(reviewDTO);
        return "redirect:/api/students/"+reviewDTO.getStudent_id()+"/AllReview/"+reviewDTO.getCourse_id();
    }

    @GetMapping("/mergeReview/{id}")
    public String mergeReview(@PathVariable int id,Model model){
        ReviewDTO reviewDTO=reviewServis.reviewFindById(id);
        model.addAttribute("reviewDTO",reviewDTO);
        return "/students/studentMergeReview-form.html";
    }

    @PostMapping("/MergeReview")
    public String MergeReview(@ModelAttribute ReviewDTO reviewDTO){
        studentServise.studentMergeReview(reviewDTO);
        return "redirect:/api/students/"+reviewDTO.getStudent_id()+"/AllReview/"+reviewDTO.getCourse_id();
    }

    @GetMapping("/deleteReview/{id}")
    public String deleteReview(@PathVariable int id){
        ReviewDTO reviewDTO=reviewServis.reviewFindById(id);
        reviewServis.reviewDeleteById(id);
        return "redirect:/api/students/"+reviewDTO.student_id+"/AllReview/"+reviewDTO.course_id;
    }

    @GetMapping("/{studentId}/deleteCourse/{id}")
    public String deleteCourseById(@PathVariable int studentId,@PathVariable int id){
        studentServise.deleteCourseById(studentId,id);
        return "redirect:/api/students/"+studentId+"/course";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id){
        studentServise.studentRemoveById(id);
        return "redirect:/api/students/"+id+"/course";
    }
}
