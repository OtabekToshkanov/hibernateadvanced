package uz.smartup.academy.hibernateadvanced.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.smartup.academy.hibernateadvanced.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
