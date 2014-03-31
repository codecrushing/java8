package com.codeslashers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Chapter7 {
	
	public static void main (String... args) throws Exception {

		User user1 = new User("Paulo Silveira", 150);
		User user2 = new User("Rodrigo Turini", 120);
		User user3 = new User("Guilherme Silveira", 90);

		List<User> users = Arrays.asList(user1, user2, user3);

		users.sort(Comparator.comparing(User::getReputationScore).reversed());
		users.subList(0,1).forEach(User::becomeModerator);

		Collections.sort(users, new Comparator<User>() {
			@Override
			public int compare(User u1, User u2) {
				return u1.getReputationScore() - u2.getReputationScore();
			}
		});

		Collections.reverse(users);
		List<User> top10 = users.subList(0, 1);
		for(User user : top10) {
			user.becomeModerator();
		}

		Stream<User> stream = users.stream()
			.filter(u -> u.getReputationScore() > 100);
	
		stream.forEach(System.out::println);

		Supplier<ArrayList<User>> supplier = ArrayList::new;
		BiConsumer<ArrayList<User>, User> accumulator = ArrayList::add;
		BiConsumer<ArrayList<User>,ArrayList<User>> combiner = ArrayList::addAll;

		users.stream()
			.filter(u -> u.getReputationScore() > 100)
			.collect(supplier, accumulator, combiner);

		users.stream()
			.filter(u -> u.getReputationScore() > 100)
			.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

		List<User> over100 = users.stream()
			.filter(u -> u.getReputationScore() > 100)
			.collect(Collectors.toList());

		ArrayList<Integer> score = new ArrayList<>();
		users.forEach(u -> score.add(u.getReputationScore()));

		List<Integer> score2 = users.stream()
			.map(u -> u.getReputationScore())
			.collect(Collectors.toList());

		List<Integer> score3 = users.stream()
			.map(User::getReputationScore)
			.collect(Collectors.toList());

		Stream<Integer> stream2 = users.stream()
			.map(User::getReputationScore);

		IntStream stream3 = users.stream()
			.mapToInt(User::getReputationScore);

		double scoreAverage = users.stream()
			.mapToInt(User::getReputationScore)
			.average()
			.getAsDouble();

		users.stream()
			.mapToInt(User::getReputationScore)
			.average()
			.orElseThrow(IllegalStateException::new);

		List<User> empty = Collections.emptyList();


		double average = empty.stream()
			.mapToInt(User::getReputationScore)
			.average()
			.orElse(0.0);

		OptionalDouble average2 = users
			.stream()
			.mapToInt(User::getReputationScore)
			.average();

		double scoreAverage2 = average2.orElse(0.0);

		Optional<User> max = users
			.stream()
			.max(Comparator.comparingInt(User::getReputationScore));

		Optional<String> maxName = users
			.stream()
			.max(Comparator.comparingInt(User::getReputationScore))
			.map(u -> u.getName());
	}
}

