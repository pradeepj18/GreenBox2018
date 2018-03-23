package com.playsoftech.greenbox.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class RefreeMarksMatch {
	private long refmm_id;
	private long ref_marks;
	private Date date;
	private Date time;
	
	private TrialRefreeLogin refreelogin;
	private Trials trials;
	private TrialMemberRegistration trialMemberRegistration;
	
	@Id
	public long getRefmm_id() {
		return refmm_id;
	}
	public void setRefmm_id(long refmm_id) {
		this.refmm_id = refmm_id;
	}
	public long getRef_marks() {
		return ref_marks;
	}
	public void setRef_marks(long ref_marks) {
		this.ref_marks = ref_marks;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Temporal(TemporalType.TIME)
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	@ManyToOne
	@JoinColumn(name="reglogin_id",nullable=false)
	public TrialRefreeLogin getRefreelogin() {
		return refreelogin;
	}
	public void setRefreelogin(TrialRefreeLogin refreelogin) {
		this.refreelogin = refreelogin;
	}
	
	@ManyToOne
	@JoinColumn(name="trial_id",nullable=false)
	public Trials getTrials() {
		return trials;
	}
	public void setTrials(Trials trials) {
		this.trials = trials;
	}
	
	@ManyToOne
	@JoinColumn(name="tmr_id",nullable=false)
	public TrialMemberRegistration getTrialMemberRegistration() {
		return trialMemberRegistration;
	}
	public void setTrialMemberRegistration(TrialMemberRegistration trialMemberRegistration) {
		this.trialMemberRegistration = trialMemberRegistration;
	}
	
	
	
	
	
}
