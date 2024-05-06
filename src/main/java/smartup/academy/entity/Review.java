package smartup.academy.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review {
    /*`id` int NOT NULL auto_increment,
	`rating` int null check(`rating`>=1 and `rating`<=10),
    `comment` varchar(256) not null,
    `course_id` int not null,
	`student_id` int not null,
    primary key (`id`),
    foreign key(`course_id`) references `course` (`id`),
    foreign key(`student_id`) references `student` (`id`)*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "rating")
    public int rating;

    @Column(name = "comment")
    public String comment;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "course_id")
    public Course course;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "student_id")
    public Student student;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

