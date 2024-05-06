package smartup.academy.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "course_student")
public class studentCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    public Course course;
    @ManyToOne
    @JoinColumn(name = "student_id")
    public Student student;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}