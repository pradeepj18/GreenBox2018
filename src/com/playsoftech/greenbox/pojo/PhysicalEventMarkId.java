package com.playsoftech.greenbox.pojo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PhysicalEventMarkId implements Serializable{
	private static final long serialVersionUID = 1L;
	private long trial_id;
	private long event_id;
	private long tmr_id; //TRial Member Registration 
	
	public long getTrial_id() {
		return trial_id;
	}
	public void setTrial_id(long trial_id) {
		this.trial_id = trial_id;
	}
	public long getEvent_id() {
		return event_id;
	}
	public void setEvent_id(long event_id) {
		this.event_id = event_id;
	}
	public long getTmr_id() {
		return tmr_id;
	}
	public void setTmr_id(long tmr_id) {
		this.tmr_id = tmr_id;
	}
	
	
}
