package com.simplesolutions.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.simplesolutions.entity.User;

@Service
public class UserServices {

	private static List<User> users = new ArrayList<>();
	private static int userid = 0;
	
	static {
		users.add(new User(++userid, "Jim Beam", LocalDate.now().minusYears(40)));
		users.add(new User(++userid, "Jack D", LocalDate.now().minusYears(30)));
		users.add(new User(++userid, "Balentines", LocalDate.now().minusYears(20)));
	}
	
	public List<User> getAllUsers() {
		return this.users;
	}
	
	public User getUserById(int id) {
		Predicate<User> predicate = user -> id == user.getId();
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public User addUser(User user) {
		User newUser = new User(++userid, user.getName(), user.getDob());
		users.add(newUser);
		return newUser;
	}
}
