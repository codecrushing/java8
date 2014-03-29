package com.codeslashers;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;


class Chapter9 {

	private static long total = 0;

	public static void main (String... args) throws Exception 	{

		LongStream lines = 
			Files.list(Paths.get("./com/codeslashers/java8"))
				.filter(p -> p.toString().endsWith(".java"))
				.mapToLong(p -> lines(p).count());

		List<Long> lines2 = 
			Files.list(Paths.get("./com/codeslashers/java8"))
				.filter(p -> p.toString().endsWith(".java"))
				.map(p -> lines(p).count())
				.collect(Collectors.toList());	

			Map<Path, Long> linesPerFile =  new HashMap<>();
			Files.list(Paths.get("./com/codeslashers/java8"))
				.filter(p -> p.toString().endsWith(".java"))
				.forEach(p -> 
					linesPerFile.put(p, lines(p).count()));
			System.out.println(linesPerFile);
			
		Map<Path, Long> linesPerFile2 = 
			Files.list(Paths.get("./com/codeslashers/java8"))
				.filter(p -> p.toString().endsWith(".java"))
				.collect(Collectors.toMap(
						Function.identity(), 
						p -> lines(p).count()));

		System.out.println(linesPerFile);


		Map<Path, List<String>> content = 
			Files.list(Paths.get("./com/codeslashers/java8"))
				.filter(p -> p.toString().endsWith(".java"))
				.collect(Collectors.toMap(
						p -> p, 
						p -> lines(p).collect(Collectors.toList())));

		User user1 = new User("Paulo Silveira", 150, true);
		User user2 = new User("Rodrigo Turini", 120, true);
		User user3 = new User("Guilherme Silveira", 90);
		User user4 = new User("Sergio Lopes", 120);
		User user5 = new User("Adriano Almeida", 100);

		List<User> users = Arrays.asList(user1, user2, user3, user4, user5);

		Map<String, User> nameToUser = users
			.stream()
			.collect(Collectors.toMap(
						User::getName, 
						Function.identity()));
		
		System.out.println(nameToUser);

		Map<Integer, List<User>> oldScore = new HashMap<>();
		
		for(User u: users) {
			if(!oldScore.containsKey(u.getReputationScore())) {
				oldScore.put(u.getReputationScore(), new ArrayList<User>());
			}
			oldScore.get(u.getReputationScore()).add(u);
		}

		System.out.println(oldScore);		

		Map<Integer, List<User>> j8Score = new HashMap<>();
		
		for(User u: users) {
			j8Score
				.computeIfAbsent(u.getReputationScore(), user -> new ArrayList<>())
				.add(u);
		}

		System.out.println(j8Score);		


		Map<Integer, List<User>> score = users
			.stream()
			.collect(Collectors.groupingBy(User::getReputationScore));

		System.out.println(users);

		Map<Boolean, List<User>> moderators = users
		 	.stream()
		 	.collect(Collectors.partitioningBy(User::isModerator));

		System.out.println(moderators);

		Map<Boolean, Integer> scoreByType = users
		 	.stream()
            .collect(Collectors.partitioningBy(u -> u.isModerator(),
            	Collectors.summingInt(User::getReputationScore)));

		System.out.println(scoreByType);

		Map<Boolean, List<String>> namesByType = users
		 	.stream()
            .collect(Collectors.partitioningBy(u -> u.isModerator(),
            	Collectors.mapping(User::getName, Collectors.toList())));

		System.out.println(namesByType);



		// PARALLEL

		List<User> filteredAndSorted = users.parallelStream()
			.filter(u -> u.getReputationScore() > 100)
			.sorted(Comparator.comparing(User::getName))
			.collect(Collectors.toList());

		long sum = 
			LongStream.range(0, 1_000_000_000)
			.filter(x -> x % 2 == 0)
			.parallel()
			.sum();
		System.out.println(sum);

	}

	static Stream<String> lines(Path p) {
		try {
			return Files.lines(p);
		} catch(IOException e) {
			throw new UncheckedIOException(e);
		}
	}

}

class UnsafeParallelStreamUsage {
	private static long total = 0;

	public static void main (String... args) throws Exception 	{
		LongStream.range(0, 1_000_000_000)
			.parallel()
			.filter(x -> x % 2 == 0)
			.forEach(n -> total += n);

		System.out.println(total);
	}
}
