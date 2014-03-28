package com.codeslashers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class User {
	
	private int reputationScore;
	private String name;
	private boolean becameModerator;
	
	public User(String name) {
		this.name = name;
	}

	public User(String name, int reputationScore) {
		this.reputationScore = reputationScore;
		this.name = name;
		this.becameModerator = false;
	}

	public User(String name, int reputationScore, boolean becameModerator) {
		this.reputationScore = reputationScore;
		this.name = name;
		this.becameModerator = becameModerator;
	}

	public int getReputationScore() {
		return reputationScore;
	}

	public String getName() {
		return name;
	}

	public void becameModerator() {
		this.becameModerator = true;
	}
	
	public String toString() {
		return "User " + name;
	}

	public boolean isModerator() {
		return becameModerator;
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