package com.challenge.challenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.challenge.challenge.model.Meeting;
import com.challenge.challenge.model.Person;
import com.challenge.challenge.model.Time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class FreetimeController {
	
	private List<Time> freetimes;
	private List<Person> peopleSchedule;
	private Date meetingTime;
	private Calendar cal;
	
	public FreetimeController(){
		freetimes=new ArrayList<Time>();
		meetingTime=new Date();
		cal = Calendar.getInstance();
		peopleSchedule=createSchedule();
		
		createDailySchedule();
	}

	@GetMapping("/freetime")
	@ResponseBody
	public List<Time> showTimes(){
		
		freetimes=calculateFreeTimes();
		return freetimes;
	}
	
	private List<Person> createSchedule(){
		
		String[] names={"Kyle","Paul","Alex","Luis","Jairo","Sonya"};
		Person p;
		List<Meeting> personMeetings;
		List<Person> people = new ArrayList<Person>();
		
		for(int i=0;i<names.length;i++){
			p=new Person();
			personMeetings=addTimesToSchedule(i);
			p.setName(names[i]);
			p.setMeeting(personMeetings);
			people.add(p);
		}
		
		return people;

	}
	
	private List<Meeting> addTimesToSchedule(int i){
		
		List<Meeting> personMeetings=new ArrayList<Meeting>();
		
		switch(i){
		case 0:
			personMeetings.add(new Meeting(setTimesForSchedule(13, 30, 0)));
			personMeetings.add(new Meeting(setTimesForSchedule(15, 30, 0)));
			personMeetings.add(new Meeting(setTimesForSchedule(18, 0, 0)));
			break;
		case 1:
			personMeetings.add(new Meeting(setTimesForSchedule(7, 0, 0)));
			personMeetings.add(new Meeting(setTimesForSchedule(9, 0, 0)));
			personMeetings.add(new Meeting(setTimesForSchedule(13, 30, 0)));
			personMeetings.add(new Meeting(setTimesForSchedule(15, 0, 0)));
			personMeetings.add(new Meeting(setTimesForSchedule(15, 30, 0)));
			break;
		case 2:
			personMeetings.add(new Meeting(setTimesForSchedule(8, 0, 0)));
			personMeetings.add(new Meeting(setTimesForSchedule(9, 30, 0)));
			personMeetings.add(new Meeting(setTimesForSchedule(12, 30, 0)));
			personMeetings.add(new Meeting(setTimesForSchedule(15, 30, 0)));
			break;
		case 3:
			personMeetings.add(new Meeting(setTimesForSchedule(9, 0, 0)));
			personMeetings.add(new Meeting(setTimesForSchedule(13, 30, 0)));
			personMeetings.add(new Meeting(setTimesForSchedule(15, 0, 0)));
			personMeetings.add(new Meeting(setTimesForSchedule(15, 30, 0)));
			break;
		case 4:
			personMeetings.add(new Meeting(setTimesForSchedule(8, 0, 0)));
			personMeetings.add(new Meeting(setTimesForSchedule(9, 0, 0)));
			personMeetings.add(new Meeting(setTimesForSchedule(18, 30, 0)));
			break;
		case 5:
			personMeetings.add(new Meeting(setTimesForSchedule(8, 0, 0)));
			personMeetings.add(new Meeting(setTimesForSchedule(12, 30, 0)));
			personMeetings.add(new Meeting(setTimesForSchedule(13, 30, 0)));
			personMeetings.add(new Meeting(setTimesForSchedule(15, 30, 0)));
			break;
		}
		
		return personMeetings;
		
	}
	
	private Date setTimesForSchedule(int hour, int min, int sec){
		
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.SECOND, sec);
		meetingTime=cal.getTime();
		
		return meetingTime;
		
	}
	
	private List<Time> calculateFreeTimes(){
		
		List<Date> dailySchedule = new ArrayList<Date>();
		List<String> freePeople = new ArrayList<String>();
		dailySchedule=createDailySchedule();
		int freeDateCounter = 0;
		boolean isPersonFree=true;
		
		for(Date d: dailySchedule){
			freeDateCounter=0;
			for (Person p : peopleSchedule) {
				isPersonFree=true;
				
				for(Meeting m : p.getMeeting()){
					if(d.equals(m.getMeetingTime())){
						isPersonFree=false;
					}
				}
				
				if(isPersonFree){
					freeDateCounter++;
					freePeople.add(p.getName());
				}
			}
			
			if(freeDateCounter >=3){
				freetimes.add(new Time(convertTimeToString(d),freePeople));
			}
			freePeople=new ArrayList<String>();
		}
		
		return freetimes;
	}
	
	private String convertTimeToString(Date time){
		
		String timeAsString="";
		String pattern="HH:mm";
		DateFormat df =new SimpleDateFormat(pattern);
		timeAsString=df.format(time);
		
		return timeAsString;
		
	}

	private List<Date> createDailySchedule() {
		// TODO Auto-generated method stub
		List<Date> dailySchedule=new ArrayList<Date>();
		Date meetingTime = setTimesForSchedule(8, 0, 0);
		Date lastOfficeDate=setTimesForSchedule(17, 0, 0);
		
		dailySchedule.add(meetingTime);
		while(meetingTime.before(lastOfficeDate)){
			cal.setTime(meetingTime);
			cal.add(Calendar.MINUTE, 30);
			meetingTime = cal.getTime();
			dailySchedule.add(meetingTime);
		}
		
		return dailySchedule;
	}
	
	
	
}
