package com.codeslashers;

import static java.lang.Math.max;
import static java.util.Comparator.comparing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.ToIntBiFunction;

class Chapter6 {

	public static void main(String[] args) {
		
		User user1 = new User("Paulo Silveira", 150);
		User user2 = new User("Rodrigo Turini", 120);
		User user3 = new User("Guilherme Silveira", 190);

		List<User> users = Arrays.asList(user1, user2, user3);

		users.forEach(u -> u.becameModerator());

		users.forEach(User::becameModerator);

		Consumer<User> becamesModerator = User::becameModerator;
		users.forEach(becamesModerator);

		users.sort(Comparator.comparing(u -> u.getName()));
		
		users.sort(Comparator.comparing(User::getName));
		
		Function<User, String> byName = User::getName;
		users.sort(comparing(byName));

		// composing comparators

		users.sort(Comparator.comparingInt(User::getReputationScore));


		Comparator<User> c = Comparator.comparingInt(User::getReputationScore)
                                 .thenComparing(User::getName);

        users.sort(Comparator.comparingInt(User::getReputationScore)
                            .thenComparing(User::getName));

        users.sort(Comparator.nullsLast(
                      	Comparator.comparing(User::getName)));

        // need typing for correct inference:
        users.sort(Comparator.comparingInt((User u) -> u.getReputationScore())
                            .thenComparing(u -> u.getName()));

 		Comparator<User> comparator = Comparator.comparing(u -> u.getReputationScore());
        users.sort(comparator.thenComparing(u -> u.getName()));

        Comparator<User> comparator2 = Comparator.comparing(u -> u.getName());
        users.sort(comparator2.reversed());

        users.sort(Comparator.comparing(User::getReputationScore).reversed());

        users.sort(Comparator.<User, Integer>comparing(u -> u.getReputationScore()).reversed());	
        
        // compiler do not infers this code: 
		// users.sort(Comparator.comparing(u -> u.getReputationScore()).reversed());

		// static methods:

		users.forEach(System.out::println);
		
		Function<String, User> userCreator = User::new;
		User rodrigo = userCreator.apply("Rodrigo Turini");
		User paulo = userCreator.apply("Paulo Silveira");
		
		BiFunction<String, Integer, User> userCreator2 = User::new;
		User rodrigo2 = userCreator2.apply("Rodrigo Turini", 50);
		User paulo2 = userCreator2.apply("Paulo Silveira", 300);
		
		Runnable block = rodrigo::becameModerator;

		Runnable block1 = rodrigo::becameModerator;
		Runnable block2 = () -> rodrigo.becameModerator();

		Consumer<User> consumer1 = User::becameModerator;
		Consumer<User> consumer2 = (u) -> u.becameModerator();

		BiFunction<Integer, Integer, Integer> max = Math::max;
		ToIntBiFunction<Integer, Integer> max2 = Math::max;
		IntBinaryOperator max3 = Math::max;
	}
}