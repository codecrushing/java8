package com.codeslashers;

import static java.lang.Integer.sum;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.IntBinaryOperator;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Chapter8 {
	
	public static void main (String... args) throws Exception {

		User user1 = new User("Paulo Silveira", 150);
		User user2 = new User("Rodrigo Turini", 120);
		User user3 = new User("Guilherme Silveira", 90);

		List<User> users = Arrays.asList(user1, user2, user3);

		List<User> filteredAndSorted = users.stream()
			.filter(u -> u.getReputationScore() > 100)
			.sorted(Comparator.comparing(User::getName))
			.collect(Collectors.toList());

		// peek display just processed:
		users.stream()
			.filter(u -> u.getReputationScore() > 100)
			.peek(System.out::println).findAny();

		System.out.println();
		
		// peek is only an intermediary:

		users.stream()
			.filter(u -> u.getReputationScore() > 100)
			.peek(System.out::println);  // need to call a terminal method!

		System.out.println();

		// sort is only an intermediary operator, but stateful:
		users.stream()
			.sorted(Comparator.comparing(User::getName))
			.peek(System.out::println)
			.findAny();

		System.out.println();

		users.stream()
			.sorted(Comparator.comparing(User::getName))
			.peek(System.out::println)
			.findAny();

		User topScore = users.stream()
			.max(Comparator.comparing(User::getReputationScore))
			.get();

		int sum = users.stream()
			.mapToInt(User::getReputationScore)
			.sum();

		int initialValue = 0;
		IntBinaryOperator operator = (a, b) -> a + b;

		int sum2 = users.stream()
			.mapToInt(User::getReputationScore)
			.reduce(initialValue, operator);

		int multiplication = users.stream()
			.mapToInt(User::getReputationScore)
			.reduce(1, (a,b) -> a * b);

		// skipping map method!
		int total3 = users.stream()
			.reduce(0, (atual, u) -> atual + u.getReputationScore(), Integer::sum);	

		boolean hasModerator = users.stream().anyMatch(User::isModerator);

		// infinite stream:

		Random random = new Random(0);
		IntStream stream = IntStream.generate(() -> random.nextInt());

		// endless loop
		// int value = stream.sum();

		List<Integer> list = IntStream
			.generate(() -> random.nextInt())
			.limit(100)
			.boxed()
			.collect(Collectors.toList());

		class Fibonacci implements IntSupplier {
			private int previous = 0;
			private int next = 1;

			public int getAsInt() {
				next = next + previous;
				previous = next - previous;
				return previous;
			}
		}

		IntStream.generate(new Fibonacci())
			.limit(10)
			.forEach(System.out::println);

		int over100 = IntStream
			.generate(new Fibonacci())
			.filter(f -> f > 100)
			.findFirst()
			.getAsInt();

		System.out.println(over100);

		IntStream.iterate(0, x -> x + 1)
			.limit(10)
			.forEach(System.out::println);		

		// flatmap e java.nio.files

		Files.list(Paths.get("./com/codeslashers/java8"))
			.forEach(System.out::println);

		Files.list(Paths.get("./com/codeslashers/java8"))
			.filter(p -> p.toString().endsWith(".java"))
			.forEach(System.out::println);


		Files.list(Paths.get("./com/codeslashers/java8"))
			.filter(p -> p.toString().endsWith(".java"))
			.map(p -> lines(p))
			.forEach(System.out::println);

		Stream<Stream<String>> strings = 
			Files.list(Paths.get("./com/codeslashers/java8"))
				.filter(p -> p.toString().endsWith(".java"))
				.map(p -> lines(p));

		Files.list(Paths.get("./com/codeslashers/java8"))
			.filter(p -> p.toString().endsWith(".java"))
			.flatMap(p -> lines(p))
			.forEach(System.out::println);

		
		IntStream chars =
			Files.list(Paths.get("./com/codeslashers/java8"))
				.filter(p -> p.toString().endsWith(".java"))
				.flatMap(p -> lines(p))
				.flatMapToInt((String s) -> s.chars());


		Group englishSpeakers = new Group();
		englishSpeakers.add(user1);
		englishSpeakers.add(user2);

		Group spanishSpeakers = new Group();
		spanishSpeakers.add(user2);
		spanishSpeakers.add(user3);

		List<Group> groups = Arrays.asList(englishSpeakers, spanishSpeakers);
		groups.stream()
			.flatMap(g -> g.getUsers().stream())
			.distinct()
			.forEach(System.out::println);

	}

	static Stream<String> lines(Path p) {
		try {
			return Files.lines(p);
		} catch(IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}

