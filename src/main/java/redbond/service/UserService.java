package redbond.service;


import java.util.List;

import redbond.model.User;

public interface UserService {
	
	//Here we are re writing our default JPA methods because we want to override and make some customization
	
	public  User findUserByEmail(String email);
	public void saveUser(User user);
	public List <User>findAll();
	public User findOneUser(int id);
	public void delete(int id);
	
	
}
