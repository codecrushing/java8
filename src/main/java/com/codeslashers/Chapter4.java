package com.codeslashers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Chapter4 {
	
	public static void main(String... args) {

		User user1 = new User("Paulo Silveira", 150);
		User user2 = new User("Rodrigo Turini", 120);
		User user3 = new User("Guilherme Silveira", 190);
	
		List<User> users = Arrays.asList(user1, user2, user3);

		Consumer<User> displayMessage = u -> 
			System.out.println("before print names");

		 Consumer<User> printName = u -> 
		 	System.out.println(u.getName());
		
 		users.forEach(displayMessage.andThen(printName));

		List<User> users2 = new ArrayList<>();
		users2.addAll(users);

 		Predicate<User> predicate = new Predicate<User>() {
 		 	public boolean test(User u) {
 		 		return u.getReputationScore() > 160;
 		 	}
 		 };

 		 users2.removeIf(predicate);
 		 users2.forEach(u -> System.out.println(u));

 		 users2.removeIf(u -> u.getReputationScore() > 160);
	}
}