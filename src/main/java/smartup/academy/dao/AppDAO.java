package smartup.academy.dao;

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

import smartup.academy.dto.UserDTO;
import smartup.academy.entity.*;

import java.util.List;

public interface AppDAO {

    public Instructor instructorFindBuId(int id);

    public List<Instructor> instructorAll();

    public void instructorPersist(Instructor instructor);

    public void instructorMerge(Instructor instructor);

    public void instructorDeleteById(int id);

    public void instructorAddCourse(Course course);

    public List<Course> instructorAllCourse(int instructorId);

    List<Instructor> instructorSearch(String a);

    /*User*/
    public void userPersist(User user);


    public void userDeleteById(User user);

    public User userFindById(int id);

    public List<User> userAll();

    public void userMerge(User user);

    void studentPersist(Student student);

    List<Student> studentAll();
    public User userFindByUserName(String userName);

    List<Role> userFindByRoles(String userName);

    public void removeRoles(String userName);

    void removeRole(Role role);

    void instructorAddCourseSave(Course course);

    Student studentFindById(int id);

    void studentAddCourse(int studentId,int courseId);

    public void studentCourseDelete(int studentId,int id);

    List<Course> studentAllCourse(int id);

    List<Course> courseAllFilterChenning(int id);

    void studentMerge(Student student);

    void studentRemoveById(int id);

    /*course*/
    public Course courseFindByid(int id);
    public List<Course> courseAll();
    public List<Review> courseAllReview(int id);
    public List<Student> courseAllStudent(int id);
    public void courseMerge(Course course);
    public void courseDelete(int id);

    /*
4. /api/reviews endpoint yarating
  GET    - /api/reviews: Hamma sharhlar olish
  GET    - /api/reviews/{reviewId}: sharhni id orqali olish
  DELETE - /api/reviews/{reviewId}: sharhni o'chirish
  */

    List<Review> reviewAll();
    Review reviewFindById(int id);
    void reviewDeleteById(int id);

    void studentAddReview(Review review);

    List<Review> studentCourseAllReview(int studentId, int courseId);

    void studentMergeReview(Review entityMerge);

    User userFindByUsername(String username);

    Student studentFindByUserId(int id);

    Instructor instructorFindByUserId(int id);

}
