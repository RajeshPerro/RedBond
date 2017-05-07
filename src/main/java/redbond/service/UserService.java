package redbond.service;

import redbond.model.User;

public interface UserService {
	
	//Here we are re writing our default JPA methods because we want to override and make some customization
	
	public  User findUserByEmail(String email);
	public void saveUser(User user);
	
}
