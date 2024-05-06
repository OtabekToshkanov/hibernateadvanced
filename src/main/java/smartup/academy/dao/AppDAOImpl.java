package smartup.academy.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import smartup.academy.dto.UserDTO;
import smartup.academy.entity.*;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{
    EntityManager entityManager;

    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Instructor instructorFindBuId(int id) {
        return entityManager.find(Instructor.class,id);
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
    public List<Instructor> instructorSearch(String a) {
        TypedQuery<Instructor> query=entityManager.createQuery("FROM Instructor i WHERE i.user.firstName LIKE :a OR i.user.firstName LIKE :a OR i.user.lastName LIKE :a OR i.user.email LIKE :a", Instructor.class);
        query.setParameter("a","%"+a+"%");
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
    public User userFindByUserName(String userName) {
        try {
            TypedQuery<User> query = entityManager.createQuery("FROM User WHERE username = :userName", User.class);
            query.setParameter("userName", userName);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Agar foydalanuvchi topilmagan bo'lsa, null qaytariladi
        }
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
    public void userDeleteById(User user) {
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
    public Student studentFindById(int id){
       return entityManager.find(Student.class,id);
    }

    @Override
    public void studentAddCourse(int studentId, int courseId) {
        Student student=studentFindById(studentId);
        Course course=courseFindByid(courseId);
        student.addCourse(course);
        entityManager.merge(student);
    }

    @Override
    public void studentCourseDelete(int studentId, int id) {
        Student student=FindByIdStudentJoinFetch(studentId);
        if(student!=null)
            student.removeCourseById(id);
    }

    private Student FindByIdStudentJoinFetch(int studentId) {
        TypedQuery<Student> query=entityManager.createQuery("""
                FROM Student s
                JOIN FETCH s.courses
                WHERE s.id=:studentId
                """, Student.class);
        query.setParameter("studentId",studentId);

        return query.getSingleResult();
    }

    @Override
    public List<Course> studentAllCourse(int id) {
        Student student=studentFindById(id);
        System.out.println(id);
        TypedQuery<Course> query=entityManager.createQuery("FROM Course WHERE :student MEMBER OF students", Course.class);
query.setParameter("student",student);
        return query.getResultList();
    }

    @Override
    public void studentAddReview(Review review) {
        entityManager.persist(review);
    }

    @Override
    public List<Review> studentCourseAllReview(int studentId, int courseId) {
        TypedQuery<Review> query=entityManager.createQuery("FROM Review WHERE student.id=:studentId and course.id=:courseId", Review.class);
        query.setParameter("studentId",studentId);
        query.setParameter("courseId",courseId);
        return query.getResultList();
    }

    @Override
    public void studentMergeReview(Review review) {
        entityManager.merge(review);
    }

    @Override
    public User userFindByUsername(String username) {
        TypedQuery<User> query=entityManager.createQuery("FROM User WHERE userName=:username",User.class);
        query.setParameter("username",username);
        return query.getSingleResult();
    }

    @Override
    public Student studentFindByUserId(int id) {
        TypedQuery<Student> query=entityManager.createQuery("FROM Student s WHERE s.user.id=:id",Student.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    public Instructor instructorFindByUserId(int id) {
        TypedQuery<Instructor> query=entityManager.createQuery("FROM Instructor i WHERE i.user.id=:id",Instructor.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }


    @Override
    public void studentRemoveById(int id) {
        Student student=studentFindById(id);
        if(student!=null)
            entityManager.remove(student);
    }

    @Override
    public List<Course> courseAllFilterChenning(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c FROM Course c WHERE NOT EXISTS (SELECT 1 FROM studentCourse cs WHERE cs.student.id = :id and cs.course.id = c.id)"
                , Course.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public void studentMerge(Student student) {
        entityManager.merge(student);
    }

    //course
    @Override
    public Course courseFindByid(int id) {
        return entityManager.find(Course.class,id);
    }

    @Override
    public List<Course> courseAll() {
        TypedQuery<Course> query=entityManager.createQuery("FROM Course", Course.class);
        return query.getResultList();
    }
    @Override
    public List<Review> courseAllReview(int id) {
        TypedQuery<Review> query=entityManager.createQuery("FROM Review WHERE course.id=:id", Review.class);
        query.setParameter("id",id);
        return query.getResultList();
    }

    @Override
    public List<Student> courseAllStudent(int id) {
        Course course=courseFindByid(id);
        TypedQuery<Student> query=entityManager.createQuery("FROM Student WHERE :course MEMBER OF courses", Student.class);
        query.setParameter("course",course);
        return query.getResultList();
    }

    @Override
    public void courseMerge(Course course) {
        entityManager.merge(course);
    }

    @Override
    public void courseDelete(int id) {
     Course course=courseFindByid(id);
     entityManager.remove(course);
    }

    //review
    @Override
    public List<Review> reviewAll() {
        TypedQuery<Review> reviewAll=entityManager.createQuery("FROM Review", Review.class);

        return reviewAll.getResultList();
    }

    @Override
    public Review reviewFindById(int id) {
        return entityManager.find(Review.class,id);
    }

    @Override
    public void reviewDeleteById(int id) {
        Review review=reviewFindById(id);
        entityManager.remove(review);
    }
}
