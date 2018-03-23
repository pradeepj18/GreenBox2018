package com.playsoftech.greenbox.pojo;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Trials {
	private long trial_id;
	private String trial_name;
	private String trial_venue;
	private Date trial_date;
	private Set<TrialMemberRegistration> trialMemberRegistration;
	private Set<PhysicalEventMarks> trialRegEvent;
	private Set<RefreeMarksMatch> refreeMarksMatch;
	
	private MatchStatus trial_status;
	
	@Id
	public long getTrial_id() {
		return trial_id;
	}
	public void setTrial_id(long trial_id) {
		this.trial_id = trial_id;
	}
	public String getTrial_name() {
		return trial_name;
	}
	public void setTrial_name(String trial_name) {
		this.trial_name = trial_name;
	}
	public String getTrial_venue() {
		return trial_venue;
	}
	public void setTrial_venue(String trial_venue) {
		this.trial_venue = trial_venue;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getTrial_date() {
		return trial_date;
	}
	public void setTrial_date(Date trial_date) {
		this.trial_date = trial_date;
	}
	

	@OneToMany(mappedBy="trials",cascade=CascadeType.ALL)
	public Set<TrialMemberRegistration> getTrialMemberRegistration() {
		return trialMemberRegistration;
	}
	public void setTrialMemberRegistration(Set<TrialMemberRegistration> trialMemberRegistration) {
		this.trialMemberRegistration = trialMemberRegistration;
	}
	
	@OneToMany(mappedBy="trials",cascade=CascadeType.ALL)
	public Set<PhysicalEventMarks> getTrialRegEvent() {
		return trialRegEvent;
	}
	public void setTrialRegEvent(Set<PhysicalEventMarks> trialRegEvent) {
		this.trialRegEvent = trialRegEvent;
	}
	
	@OneToMany(mappedBy="trials",cascade=CascadeType.ALL)
	public Set<RefreeMarksMatch> getRefreeMarksMatch() {
		return refreeMarksMatch;
	}
	public void setRefreeMarksMatch(Set<RefreeMarksMatch> refreeMarksMatch) {
		this.refreeMarksMatch = refreeMarksMatch;
	}
	

	@Enumerated(EnumType.STRING)
	public MatchStatus getTrial_status() {
		return trial_status;
	}
	public void setTrial_status(MatchStatus trial_status) {
		this.trial_status = trial_status;
	}

	
	
}
