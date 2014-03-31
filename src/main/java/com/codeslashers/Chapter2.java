package com.codeslashers;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Chapter2 {
	
	public static void main(String... args) {
		
		User user1 = new User("Paulo Silveira", 150);
		User user2 = new User("Rodrigo Turini", 120);
		User user3 = new User("Guilherme Silveira", 190);

		List<User> users = Arrays.asList(user1, user2, user3);

		for (User user : users) {
			System.out.println(user.getName());
		}
		
		users.forEach(new Consumer<User>() {
			public void accept(User user) {
				System.out.println(user.getName());
			}
		});
		
		users.forEach(u -> System.out.println(u.getName()));
		
		users.forEach(u -> u.becomeModerator());
		
	}
}