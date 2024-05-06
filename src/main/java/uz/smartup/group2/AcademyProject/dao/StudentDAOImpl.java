package uz.smartup.group2.AcademyProject.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uz.smartup.group2.AcademyProject.entity.Course;
import uz.smartup.group2.AcademyProject.entity.Review;
import uz.smartup.group2.AcademyProject.entity.Student;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    @Autowired
    private DataSource dataSource;
    private final EntityManager entityManager;

    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void deleteById(int id) {
        Student student = findById(id);
        entityManager.remove(student);
    }

    @Override
    public void addCourse(int courseId, int studentId) {
        Student student= findById(studentId);
        Course course = entityManager.find(Course.class, courseId);
        student.addCourses(course);
    }

    @Override
    public void removeCourseFromStudent(int studentId, int courseId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            String sql = "DELETE FROM course_student WHERE student_id = ? AND course_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addReviewToCourseFromStudent(Review review, int courseId, int studentId) {
        review.setStudentId(studentId);
        review.setCourseId(courseId);
        entityManager.persist(review);
    }

    @Override
    public List<Course> getStudentCourses(int id) {
        Student student = findById(id);
        TypedQuery<Course> query = entityManager.createQuery("FROM Course WHERE :student MEMBER OF students", Course.class);
        query.setParameter("student", student);
        return query.getResultList();
    }

}
