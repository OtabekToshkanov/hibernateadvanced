package smartup.academy.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "course_student",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns =@JoinColumn(name = "course_id"))
    public List<Course> courses;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    public List<Review> reviews;

    public void addCourse(Course course){
        if(courses==null){
            courses=new ArrayList<>();
        }
        courses.add(course);
    }

    public void removeCourse(Course course){
        if(courses!=null)
            courses.remove(course);
    }

    public void addCourses(List<Course> newCourses){
        if(courses==null)
            courses=new ArrayList<>();
        courses.addAll(newCourses);
    }
    public void removeCourseById(int courseId){
        if(courses!=null)
            courses.removeIf(course -> course.getId()==courseId);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", user=" + user +
                ", courses=" + courses +
                ", reviews=" + reviews +
                '}';
    }
}
