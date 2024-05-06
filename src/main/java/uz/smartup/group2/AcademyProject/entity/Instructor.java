    package uz.smartup.group2.AcademyProject.entity;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.util.ArrayList;
    import java.util.List;


    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @Table(name = "instructor")
    public class Instructor {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;
        @Column(name = "first_name")
        private String firstName;
        @Column(name = "last_name")
        private String lastName;
        @Column(name = "email", unique = true)
        private String email;
        @Column(name = "password")
        private String password;
        @Column(name = "role")
        private String role;
        @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private List<Course> courses;
        public Instructor(String firstName, String lastName, String email, String password, List<Course> courses) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.courses = courses;
        }
    }
