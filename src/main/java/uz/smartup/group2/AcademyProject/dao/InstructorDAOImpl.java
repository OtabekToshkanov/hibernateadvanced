package uz.smartup.group2.AcademyProject.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import uz.smartup.group2.AcademyProject.entity.Course;
import uz.smartup.group2.AcademyProject.entity.Instructor;
import uz.smartup.group2.AcademyProject.entity.Review;

import java.util.List;

@Repository
public class InstructorDAOImpl implements InstructorDAO{
    private final EntityManager entityManager;
    private final CourseDAO courseDAO;
    public InstructorDAOImpl(EntityManager entityManager, CourseDAO courseDAO) {
        this.entityManager = entityManager;
        this.courseDAO = courseDAO;
    }
    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    public void deleteById(int id) {
        Instructor instructor = findInstructorById(id);
        entityManager.remove(instructor);
    }

    @Override
    public List<Review> getAllCourseReviews(int courseId) {
        TypedQuery<Review> query = entityManager.createQuery(
                "SELECT r FROM Review r WHERE r.courseId = :courseId", Review.class);
        query.setParameter("courseId", courseId);
        return query.getResultList();
    }

    @Override
    public Review getById(int reviewId) {
        return entityManager.find(Review.class, reviewId);
    }
}
