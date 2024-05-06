package uz.smartup.group2.AcademyProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.smartup.group2.AcademyProject.entity.Instructor;
import uz.smartup.group2.AcademyProject.entity.Student;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
     Instructor findByEmail(String email);
}
