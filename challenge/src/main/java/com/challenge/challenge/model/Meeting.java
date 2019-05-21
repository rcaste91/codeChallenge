package com.challenge.challenge.model;

import java.util.Date;

public class Meeting {

	private Date meetingTime;
	
	public Meeting(){
		
	}
	
	public Meeting(Date meetingTime){
		this.setMeetingTime(meetingTime);
	}

	public Date getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(Date meetingTime) {
		this.meetingTime = meetingTime;
	}
	
}
