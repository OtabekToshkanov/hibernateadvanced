package uz.smartup.academy.hibernateadvanced.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import uz.smartup.academy.hibernateadvanced.entity.*;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    private final EntityManager entityManager;

    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Instructor instructorFindBuId(int id) {
        return entityManager.find(Instructor.class,id);
    }

    @Override
    public List<Instructor> findInstructorByFirstname(String firstName) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "SELECT i FROM Instructor i WHERE i.user.firstName LIKE :firstName",
                Instructor.class);
        query.setParameter("firstName", firstName + "%");
        return query.getResultList();
    }



    @Override
    public List<Instructor> instructorAll() {
        TypedQuery<Instructor> query=entityManager.createQuery("FROM Instructor", Instructor.class);
        return query.getResultList();
    }

    @Override
    public void instructorPersist(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public void instructorMerge(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    public void instructorDeleteById(int id) {
        entityManager.remove(instructorFindBuId(id));
    }

    @Override
    public List<Course> instructorAllCourse(int instructorId) {
        TypedQuery<Course> query=entityManager.createQuery("FROM Course WHERE instructor.id = :instructorId", Course.class);
        query.setParameter("instructorId",instructorId);
        return query.getResultList();
    }

    @Override
    public List<Course> findInstructorCourseBuTitle(int instructorId, String title) {
        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c FROM Course c WHERE c.instructor.id = :instructorId AND c.title LIKE :title",
                Course.class);
        query.setParameter("title", "%" + title + "%");
        query.setParameter("instructorId", instructorId);
        return query.getResultList();
    }


    @Override
    public void instructorAddCourse(Course course) {
        entityManager.persist(course);
    }


    @Override
    public void instructorAddCourseSave(Course course) {
        entityManager.persist(course);
    }


    /* User servise*/

    @Override
    public void userPersist(User user) {
        entityManager.persist(user);
    }

    @Override
    public User userFindById(int id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public User userFindByUserName(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.userName =:username", User.class
        );
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    @Override
    public List<User> userFindByFirstName(String firstName) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.firstName LIKE :firstName", User.class);
        query.setParameter("firstName", firstName + "%");
        return query.getResultList();
    }



    @Override
    public List<Role> userFindByRoles(String userName) {
        TypedQuery<Role> query = entityManager.createQuery(
                "SELECT r FROM Role r WHERE r.id.username = :userName", Role.class);
        query.setParameter("userName", userName);
        return query.getResultList();
    }

    @Override
    public void removeRoles(String userName) {
        TypedQuery<Role> query = entityManager.createQuery(
                "SELECT r FROM Role r WHERE r.id.username = :userName", Role.class);
        query.setParameter("userName", userName);

        for(Role role: query.getResultList()){
            entityManager.remove(role);
        }
    }

    @Override
    public void removeRole(Role role) {
        entityManager.remove(role);
    }




    @Override
    public List<User> userAll() {
        TypedQuery<User> query=entityManager.createQuery("FROM User",User.class);
        return query.getResultList();
    }

    @Override
    public void UserDeleteById(User user) {
        entityManager.remove(user);
    }

    @Override
    public void userMerge(User user) {
        entityManager.merge(user);
    }

    /*Student*/
    @Override
    public void studentPersist(Student student) {
        entityManager.persist(student);
    }

    @Override
    public List<Student> studentAll() {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student", Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findStudentByFirstName(String firstName) {
        TypedQuery<Student> query = entityManager.createQuery(
                "SELECT i FROM Student i WHERE i.user.firstName LIKE :firstName",
                Student.class);
        query.setParameter("firstName", firstName + "%");
        return query.getResultList();
    }


    @Override
    public Student findStudentById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    @Override
    public void deleteStudentById(int id) {
        Student student = findStudentById(id);
        entityManager.remove(student);
    }

    @Override
    public void addCourseInStudent(int studentId, int courseId) {
        Student student =findStudentById(studentId);
        Course course =entityManager.find(Course.class, courseId);

        student.addCourse(course);

        updateStudent(student);
    }

    @Override
    public List<Course> getStudentCourses(int id) {
        Student student = findStudentById(id);

        TypedQuery<Course> query = entityManager.createQuery("FROM Course WHERE :student MEMBER OF students", Course.class);
        query.setParameter("student", student);

        return query.getResultList();
    }

    @Override
    public void deleteStudentFromCourse(int studentId, int courseId) {
        Student student =findStudentByIdJoinFetchCourses(studentId);
        student.removeCourseById(courseId);

        updateStudent(student);
    }

    private Student findStudentByIdJoinFetchCourses(int id) {
        TypedQuery<Student> query = entityManager.createQuery("""
                FROM Student s
                JOIN FETCH s.courses
                WHERE s.id = :id
                """, Student.class);

        query.setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    public void addStudentReview(int studentId, int courseId, Review review) {
//        review.setStudent(studentId);
//        review.setCourseId(courseId);
        entityManager.persist(review);
    }

    @Override
    public List<Review> getStudentReviews(int studentId, int courseId) {
        TypedQuery<Review> query = entityManager.createQuery("""
                 FROM Review
                    WHERE student.id = :studentId
                    AND course.id = :courseId
                 """, Review.class);

        query.setParameter("studentId", studentId);
        query.setParameter("courseId", courseId);

        return query.getResultList();
    }

    @Override
    public void updateStudentReview(int studentId, int courseId, Review review) {
//        review.setStudentId(studentId);
//        review.setCourseId(courseId);
        entityManager.merge(review);
    }

    @Override
    public List<Course> getAllCourses() {
        TypedQuery<Course> query = entityManager.createQuery("FROM Course", Course.class);
        return query.getResultList();
    }

    @Override
    public Course findCourseById(int id) {
        Course course=entityManager.find(Course.class, id);
        return course;
    }

    @Override
    public List<Course> getCoursesFilteredByStudent(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c FROM Course c WHERE NOT EXISTS (SELECT 1 FROM CourseStudent cs WHERE cs.student.id = :id and cs.course.id = c.id)"
                , Course.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Review> getReviewsByCourseId(int id) {
        TypedQuery<Review> query = entityManager.createQuery("FROM Review WHERE courseId = :id", Review.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Student> getStudentsByCourseId(int id) {
        Course course = findCourseById(id);
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE :course MEMBER OF courses", Student.class);
        query.setParameter("course", course);

        return query.getResultList();
    }

    @Override
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    public void deleteCourseById(int id) {
        Course course = findCourseById(id);
        //System.out.println(course);
        entityManager.remove(course);
    }
    @Override
    public void deleteReviewsByCourseId(int courseId) {
        String hql = "delete from Review where course.id = :courseId";
        Query query = entityManager.createQuery(hql);
        query.setParameter("courseId", courseId);
        query.executeUpdate();
    }

    @Override
    public List<Review> getAllReviews() {
        TypedQuery<Review> query = entityManager.createQuery("FROM Review", Review.class);
        return query.getResultList();
    }

    @Override
    public Review findReviewsById(int id) {
        return entityManager.find(Review.class,id);
    }

    @Override
    public void deleteReviewById(int id) {
        Review review = findReviewsById(id);
        //System.out.println(review);
        entityManager.remove(review);
    }

}
