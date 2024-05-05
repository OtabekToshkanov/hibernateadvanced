package uz.smartup.academy.hibernateadvanced.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import uz.smartup.academy.hibernateadvanced.dto.CourseDTO;
import uz.smartup.academy.hibernateadvanced.entity.*;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {
    private final EntityManager entityManager;

    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        TypedQuery<Instructor> query = entityManager.createQuery("FROM Instructor", Instructor.class);
        return query.getResultList();
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    public void deleteInstructorById(int id) {
        Instructor instructor = findInstructorById(id);
        System.out.println(instructor);

        entityManager.remove(instructor);
    }

    @Override
    public void save(Course course) {
        System.out.println(course);
        entityManager.persist(course);
    }

    @Override
    public List<Course> getAllCourcesByInstructorId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("FROM Course WHERE instructor.id = :id", Course.class);
        query.setParameter("id", id);

        return query.getResultList();
    }

    @Override
    public void save(Student student) {
//        System.out.println(student);

        entityManager.persist(student);
    }

    @Override
    public List<Student> getAllStudents() {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student", Student.class);
        return query.getResultList();
    }

    @Override
    public Student findStudentById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    public void deleteStudentById(int id) {
        Student student = findStudentById(id);
        entityManager.remove(student);
    }

    @Override
    public void addStudentToCourse(int studentId, int courseId) {
        Student student = findStudentById(studentId);
        Course course = entityManager.find(Course.class, courseId);

        student.addCourse(course);

        update(student);
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
        Student student = findStudentByIdJoinFetchCourses(studentId);
        student.removeCourseById(courseId);
        update(student);
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
        review.setStudentId(studentId);
        review.setCourseId(courseId);
        entityManager.persist(review);
    }

    @Override
    public List<Review> getStudentReviews(int studentId, int courseId) {
        TypedQuery<Review> query = entityManager.createQuery("""
                 FROM Review
                WHERE studentId = :studentId
                  AND courseId = :courseId
                 """, Review.class);

        query.setParameter("studentId", studentId);
        query.setParameter("courseId", courseId);

        return query.getResultList();
    }

    @Override
    public void updateStudentReview(int studentId, int courseId, Review review) {
        review.setStudentId(studentId);
        review.setCourseId(courseId);
        entityManager.merge(review);
    }

    @Override
    public List<Course> getAllCourses() {
        TypedQuery<Course> query = entityManager.createQuery("FROM Course", Course.class);
        return query.getResultList();
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public List<Review> getReviewsByCourseId(int id) {
        TypedQuery<Review> query = entityManager.createQuery("FROM Review WHERE courseId = :id", Review.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Student> getStudentsByCourseId(int id) {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE :course MEMBER OF courses", Student.class);
        query.setParameter("course", findCourseById(id));

        return query.getResultList();
    }

    @Override
    public void updateCourse(Course course) {
        Course course1 = course;
        entityManager.merge(course1);
    }

    @Override
    public void deleteReview(int id) {
        Review review = entityManager.find(Review.class, id);

        entityManager.remove(review);
    }

    @Override
    public void deleteReviewById(int id) {
        Review review = entityManager.find(Review.class, id);
        entityManager.remove(review);
    }

    @Override
    public void deleteCourseById(int id) {
        Course course = findCourseById(id);
        course.setReviews(getReviewsByCourseId(id));
        entityManager.remove(course);
    }

    @Override
    public void deleteReviewsByCourseId(int id) {
        Query query = entityManager.createQuery("DELETE FROM Review r where r.courseId = :id");
        query.setParameter("id", id);
    }

    @Override
    public List<Course> listAnotherCoursesByStudentId(int id) {
        Student student = findStudentById(id);
        TypedQuery<Course> query = entityManager.createQuery("FROM Course WHERE :student not MEMBER OF students", Course.class);
        query.setParameter("student", student);

        return query.getResultList();
    }

    //    @Override
//    public void save(InstructorDetail detail) {
//        entityManager.persist(detail);
//    }
//
//    @Override
//    public InstructorDetail findInstructorDetailById(int id) {
//        return entityManager.find(InstructorDetail.class, id);
//    }
//
//    @Override
//    public List<Course> findCoursesByInstructorId(int id) {
//        TypedQuery<Course> query = entityManager.createQuery("FROM Course WHERE instructor.id = :id", Course.class);
//
//        query.setParameter("id", id);
//
//        return query.getResultList();
//    }
//
//    @Override
//    public Instructor findInstructorByIdJoinFetch(int id) {
//        TypedQuery<Instructor> query = entityManager.createQuery("""
//                 FROM Instructor i
//                 JOIN FETCH i.courses
//                WHERE i.id = :id
//                 """, Instructor.class);
//
//        query.setParameter("id", id);
//
//        return query.getSingleResult();
//    }
//
//    @Override
//    public void save(Review review) {
//
//    }
//
//    @Override
//    public Course findCourseById(int id) {
//        return entityManager.find(Course.class, id);
//    }
//
//    @Override
//    public Course findCourseByIdJoinFetch(int id) {
//        TypedQuery<Course> query = entityManager.createQuery(
//                """
//                        FROM Course c
//                        LEFT JOIN FETCH c.reviews
//                        JOIN FETCH c.instructor
//                        WHERE c.id = :id
//                        """, Course.class
//        );
//
//        query.setParameter("id", id);
//
//        return query.getSingleResult();
//    }
//
//    @Override
//    public List<Review> findReviewsByCourseId(int id) {
//        TypedQuery<Review> query = entityManager.createQuery("FROM Review r WHERE r.courseId = :id", Review.class);
//        query.setParameter("id", id);
//        return query.getResultList();
//    }
//
//    @Override
//    public Review findReviewById(int id) {
//        return null;
//    }
//
//    @Override
//    @Transactional
//    public void update(Course course) {
//        entityManager.merge(course);
//    }
}
