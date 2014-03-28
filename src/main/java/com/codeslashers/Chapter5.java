package com.codeslashers;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

class Chapter5 {

	public static void main(String[] args) {
		
		User user1 = new User("Paulo Silveira", 150);
		User user2 = new User("Rodrigo Turini", 120);
		User user3 = new User("Guilherme Silveira", 190);
		
		List<User> users = Arrays.asList(user1, user2, user3);
		
		Comparator<User> comparator = new Comparator<User>() {
			public int compare(User u1, User u2) {
				return u1.getName().compareTo(u2.getName());
			}	
		};
		
		Collections.sort(users, comparator);

		users.sort(comparator);

		Comparator<User> comparatorUsingLambda = 
			  (u1, u2) -> u1.getName().compareTo(u2.getName());

		Collections.sort(users, comparatorUsingLambda);
		
		Collections.sort(users, 
				(u1, u2) -> u1.getName().compareTo(u2.getName()));
		
		users.sort((u1, u2) -> u1.getName().compareTo(u2.getName()));
		
		users.sort(Comparator.comparing(u -> u.getName()));	

		users.sort(Comparator.comparing(u -> u.getReputationScore()));

		List<String> list = Collections.emptyList();
		list.sort(Comparator.comparing((String s)->s.toString())
        			.thenComparing(s -> s.length()));

		Function<User, Integer> getScores = u -> u.getReputationScore();
		Comparator<User> comparator2 = Comparator.comparing(getScores);
		users.sort(comparator2);

		ToIntFunction<User> getScores2 = u -> u.getReputationScore();
		Comparator<User> comparator4 = Comparator.comparingInt(getScores2);
		users.sort(comparator4);

		users.sort(Comparator.comparingInt(u -> u.getReputationScore()));

		// natural order:
		List<String> words = Arrays.asList("CodeSlashers", "Mamute");

    	words.sort(Comparator.naturalOrder());
    	
    	// reverse order:
    	words.sort(Comparator.reverseOrder());

	}
}
