package redbond.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import redbond.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	
	//Custom methods those are need for our application
	
		User findByEmail(String email);
}
