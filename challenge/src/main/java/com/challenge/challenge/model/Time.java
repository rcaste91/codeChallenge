package com.challenge.challenge.model;

import java.util.List;

public class Time {

	private String time;
	private List<String> personName;
	
	public Time(String time, List<String> people){
		this.setTime(time);
		this.setPersonName(people);
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<String> getPeople() {
		return personName;
	}

	public void setPersonName(List<String> people) {
		this.personName = people;
	}

	

	
	
}
