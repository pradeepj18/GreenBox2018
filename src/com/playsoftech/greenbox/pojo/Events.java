package com.playsoftech.greenbox.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Events {
	private long event_id;
	private String event_name;
	
	private Set<PhysicalEventMarks> trialRegEvent; 
	
	@Id
	public long getEvent_id() {
		return event_id;
	}
	public void setEvent_id(long event_id) {
		this.event_id = event_id;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	
	@OneToMany(mappedBy="events",cascade=CascadeType.ALL)
	public Set<PhysicalEventMarks> getTrialRegEvent() {
		return trialRegEvent;
	}
	public void setTrialRegEvent(Set<PhysicalEventMarks> trialRegEvent) {
		this.trialRegEvent = trialRegEvent;
	}
	
	
}
