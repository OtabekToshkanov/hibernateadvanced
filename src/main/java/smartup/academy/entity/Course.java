package smartup.academy.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/*
Course nima?
  - id
  - title
  - description // course jadvaliga bu ustunni qo'shing
  - price // course jadvaliga bu ustunni qo'shing
  - instructorId
  - reviews // course yaratish va yangilash vaqtida, so'rov va javob qismida bu obyekt qatnashmasin.
  - students // course yaratish va yangilash vaqtida, so'rov va javob qismida bu obyekt qatnashmasin.
*/

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "title")
    public String title;

    @Column(name = "description")
    public String description;

    @Column(name = "prise")
    public int price;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    public Instructor instructor;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    public List<Review> reviews;

    @ManyToMany(cascade ={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH} )
    @JoinTable(name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    public List<Student> students;

    public void addStudent(Student student){
        if(students==null){
            students=new ArrayList<>();
        }
        students.add(student);
    }
    public void removeStudent(Student student){
        if(students!=null)
            students.remove(student);
    }

    public void addStudents(List<Student> newStudents){
        if(students==null)
            students=new ArrayList<>();
        students.addAll(newStudents);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", instructor=" + instructor +
                ", reviews=" + reviews +
                ", students=" + students +
                '}';
    }
}

