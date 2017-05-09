package redbond.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import redbond.model.User;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
	
	//Custom methods those are need for our application
	
		User findByEmail(String email);
		List <User> findByBloodgroup(String bloodgroup);
		
		
}
