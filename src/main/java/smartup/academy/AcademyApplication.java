package smartup.academy;

import jakarta.transaction.Transactional;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import smartup.academy.dao.AppDAO;
import smartup.academy.entity.User;
import smartup.academy.security.UserSecurity;

import java.util.List;

@SpringBootApplication
public class AcademyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademyApplication.class, args);
	}
//	@Transactional
//	@Bean
//	public ApplicationRunner applicationRunner(AppDAO appDAO, PasswordEncoder passwordEncoder){
//		return args -> {
//			User user= appDAO.userFindById(1);
//			System.out.println(user);
//			user.setPassword(passwordEncoder.encode("1"));
//			System.out.println(user);
//			appDAO.userMerge(user);
//		};
//	}

}
