package com.codeslashers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class User {
	
	private int reputationScore;
	private String name;
	private boolean becomeModerator;
	
	public User(String name) {
		this.name = name;
	}

	public User(String name, int reputationScore) {
		this.reputationScore = reputationScore;
		this.name = name;
		this.becomeModerator = false;
	}

	public User(String name, int reputationScore, boolean becomeModerator) {
		this.reputationScore = reputationScore;
		this.name = name;
		this.becomeModerator = becomeModerator;
	}

	public int getReputationScore() {
		return reputationScore;
	}

	public String getName() {
		return name;
	}

	public void becomeModerator() {
		this.becomeModerator = true;
	}
	
	public String toString() {
		return "User " + name;
	}

	public boolean isModerator() {
		return becomeModerator;
	}
}

class Group {
	private Set<User> users = new HashSet<>();

	public void add(User u) {
		users.add(u);
	}

	public Set<User> getUsers() {
		return Collections.unmodifiableSet(this.users);
	}
}