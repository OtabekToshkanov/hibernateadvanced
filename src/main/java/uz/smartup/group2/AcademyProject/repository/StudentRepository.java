package uz.smartup.group2.AcademyProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.smartup.group2.AcademyProject.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
     Student findByEmail(String email);
}
