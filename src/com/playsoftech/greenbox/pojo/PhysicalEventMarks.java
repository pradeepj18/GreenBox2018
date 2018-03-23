package com.playsoftech.greenbox.pojo;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PhysicalEventMarks {
	private PhysicalEventMarkId trialRegEventId;
	/*private Date time;*/
	private String time;
	private Date date;
	private int score;
	private TrialPlayerStatus playerstatus;
	private double mark;
	
	private Trials trials;
	private Events events;
	private TrialMemberRegistration trialMemberRegistration;
	
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride( name = "trial_id",
							column = @Column(name = "trial_id",nullable = false)),
		@AttributeOverride( name = "event_id",
							column = @Column(name = "event_id" ,nullable = false)),
		@AttributeOverride( name = "tmr_id",
							column = @Column(name = "tmr_id",nullable = false))
	})
	
	public PhysicalEventMarkId getTrialRegEventId() {
		return trialRegEventId;
	}
	public void setTrialRegEventId(PhysicalEventMarkId trialRegEventId) {
		this.trialRegEventId = trialRegEventId;
	}
	
	/*@Temporal(TemporalType.TIME)
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	*/
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	/*public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}*/
	
	public double getMark() {
		return mark;
	}
	public void setMark(double mark) {
		this.mark = mark;
	}
	
	@Enumerated(EnumType.STRING)
	public TrialPlayerStatus getPlayerstatus() {
		return playerstatus;
	}
	
	public void setPlayerstatus(TrialPlayerStatus playerstatus) {
		this.playerstatus = playerstatus;
	}
	@ManyToOne
	@JoinColumn(name="trial_id",nullable=false, insertable = false, updatable = false)
	public Trials getTrials() {
		return trials;
	}
	public void setTrials(Trials trials) {
		this.trials = trials;
	}
	
	@ManyToOne
	@JoinColumn(name="event_id",nullable=false, insertable = false, updatable = false)
	public Events getEvents() {
		return events;
	}
	public void setEvents(Events events) {
		this.events = events;
	}
	
	@ManyToOne
	@JoinColumn(name="tmr_id",nullable=false, insertable = false, updatable = false)
	public TrialMemberRegistration getTrialMemberRegistration() {
		return trialMemberRegistration;
	}
	public void setTrialMemberRegistration(TrialMemberRegistration trialMemberRegistration) {
		this.trialMemberRegistration = trialMemberRegistration;
	}
	
	
}