package uz.smartup.group2.AcademyProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.smartup.group2.AcademyProject.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
