package ch.wiss.m223.wiss_quiz2024;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.wiss.m223.wiss_quiz2024.model.ERole;
import ch.wiss.m223.wiss_quiz2024.model.Role;
import ch.wiss.m223.wiss_quiz2024.model.User;
import ch.wiss.m223.wiss_quiz2024.repositories.RoleRepository;
import ch.wiss.m223.wiss_quiz2024.repositories.UserRepository;

@RestController
@SpringBootApplication
public class WissQuiz2024Application implements CommandLineRunner{

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(WissQuiz2024Application.class, args);
	}

	@GetMapping("/api")
	public ResponseEntity<List<String>> helloWorld(){
		List<String> demo = List.of("alpha", "beta", "hurra");
		return ResponseEntity.ok(demo);
	}

	@GetMapping("/api/private")
	public ResponseEntity<String> privateArea(){
		return ResponseEntity.ok("Geheim");
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		if (roleRepository.count() == 0) {
			roleRepository.save(new Role(ERole.ROLE_USER));
			roleRepository.save(new Role(ERole.ROLE_ADMIN));
		}

		if (userRepository.count() == 0) {
			userRepository.save(new User("user", "user", encoder.encode("user")));
			userRepository.save(new User("admin", "admin", encoder.encode("admin")));
		}
	}

}
