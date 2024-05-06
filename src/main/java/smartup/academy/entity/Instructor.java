package smartup.academy.entity;

import jakarta.persistence.*;

import java.util.List;

/*InstructorDTO nima?
  - id
  - name
  - email
  - youtubeChannel // instructor_detail jadvalidan
  - hobby // instructor_detail jadvalidan
  - courses // instructor'ni yaratish, yangilash va olish mobaynida, so'rov va javob qismida bu obyekt qatnashmasin.*/
@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    public User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="instructor_detail_id")
    public InstructorDetail instructorDetail;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "instructor")
    public List<Course> courses;

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

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", user=" + user +
                ", instructorDetail=" + instructorDetail +
                ", courses=" + courses +
                '}';
    }
}

