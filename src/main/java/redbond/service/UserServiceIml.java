package redbond.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import redbond.model.Role;

import redbond.model.User;
import redbond.repository.RoleRepository;
import redbond.repository.UserRepository;

@Service("userService")
public class UserServiceIml implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		redbond.model.Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public List<User> findAll()
	{
		List<User> users = new ArrayList<User> ();
		for(User user: userRepository.findAll())
		{
			users.add(user);
		}
	return users;	
	}
	
	@Override
	public void delete(int id)
	{
		userRepository.delete((long) id);
	}

	@Override
	public User findOneUser(int id) {
		// TODO Auto-generated method stub
		return userRepository.findOne((long) id);
	}

	@Override
	public List<User> findByBloodgroup(String bloodgroup) {

		List<User> users = new ArrayList<User> ();
		for(User user: userRepository.findByBloodgroup(bloodgroup))
		{
			users.add(user);
		}
	return users;	
	}
}
