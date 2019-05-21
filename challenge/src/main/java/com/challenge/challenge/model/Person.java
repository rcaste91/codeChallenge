package com.challenge.challenge.model;

import java.util.List;

public class Person {

	private String name;
	private List<Meeting> meeting;
	
	public Person(){
		
	}
	
	public Person(String name, List<Meeting> meeting){
		this.setName(name);
		this.setMeeting(meeting);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Meeting> getMeeting() {
		return meeting;
	}

	public void setMeeting(List<Meeting> meeting) {
		this.meeting = meeting;
	}
}
