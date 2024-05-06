package smartup.academy.serves;

import smartup.academy.dto.CourseDTO;
import smartup.academy.dto.InstructorDTO;

import java.util.List;

public interface InstructorServis {
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
    public void instructorPersist(InstructorDTO instructorDTO);

    public InstructorDTO instructorFindById(int id);

    public List<InstructorDTO> instructorAll();

    public void instructorMerge(InstructorDTO instructorDTO);

    public void instructorDeleteById(int id);

    public void instructorAddCourse(CourseDTO courseDTO);

    public List<CourseDTO> instructorAllCourse(int id);

    void instructorAddCourseSave(CourseDTO courseDTO);

    List<InstructorDTO> instructorSearch(String a);

    InstructorDTO instructorFindByUserId(int id);

}
