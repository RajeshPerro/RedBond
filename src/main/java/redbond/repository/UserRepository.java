package redbond.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import redbond.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
	
	//Custom methods those are need for our application
	
		User findByEmail(String email);
		List <User> findByBloodgroup(String bloodgroup);
		User findById(int id);
		

		@Modifying
	    @Query("UPDATE User u SET u.name =?1, u.phone=?2, u.bloodgroup=?3, u.country=?4,"
	    		+ "u.city=?5, u.latitude=?6, u.longitude=?7 WHERE u.id =?8")
	    int update(String name, String phone, String bloodgroup, String country, String city, String latitude,String longitude, int id);
}
