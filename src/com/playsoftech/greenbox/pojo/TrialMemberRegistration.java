package com.playsoftech.greenbox.pojo;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class TrialMemberRegistration {
	private long tmr_id;
	private String fname;
	private String mname;
	private String lname;
	private Date regdate;
	private Date dob;
	private String photo;
	private Gender gender;
	private MemCategory memcategory; // PLAYER,OFFICIAL
	private MemStatus memstatus;	//OLD and NEW
	private long barcode_id;
	private TrialPlayerStatus playerstatus;			//NOTPLAYED,APPEARING,INJURED,PLAYED (for match only)
	private AuctionStatus auctionstatus;			//SELECTED,UNSELECTED
	
	private Trials trials;
	private Set<PhysicalEventMarks> trialRegEvent;
	private Set<RefreeMarksMatch> refreeMarksMatch;
	
	private String position;
	private String Batch;
	private String email;
	private String mobileno;
	private String address;
	
	private long dummy_id;
	
	@Id
	public long getTmr_id() {
		return tmr_id;
	}
	public void setTmr_id(long tmr_id) {
		this.tmr_id = tmr_id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	@Enumerated(EnumType.STRING)
	public MemCategory getMemcategory() {
		return memcategory;
	}
	public void setMemcategory(MemCategory memcategory) {
		this.memcategory = memcategory;
	}
	
	@Enumerated(EnumType.STRING)
	public MemStatus getMemstatus() {
		return memstatus;
	}
	public void setMemstatus(MemStatus memstatus) {
		this.memstatus = memstatus;
	}
	public long getBarcode_id() {
		return barcode_id;
	}
	public void setBarcode_id(long barcode_id) {
		this.barcode_id = barcode_id;
	}
	
	@Enumerated(EnumType.STRING)
	public TrialPlayerStatus getPlayerstatus() {
		return playerstatus;
	}
	public void setPlayerstatus(TrialPlayerStatus playerstatus) {
		this.playerstatus = playerstatus;
	}
	
	@Enumerated(EnumType.STRING)
	public AuctionStatus getAuctionstatus() {
		return auctionstatus;
	}
	public void setAuctionstatus(AuctionStatus auctionstatus) {
		this.auctionstatus = auctionstatus;
	}
	
	@ManyToOne(cascade  = CascadeType.ALL)
	@JoinColumn(name="trial_id",nullable=false)
	public Trials getTrials() {
		return trials;
	}
	public void setTrials(Trials trials) {
		this.trials = trials;
	}
	
	@OneToMany(mappedBy="trialMemberRegistration",cascade=CascadeType.ALL)
	public Set<PhysicalEventMarks> getTrialRegEvent() {
		return trialRegEvent;
	}
	public void setTrialRegEvent(Set<PhysicalEventMarks> trialRegEvent) {
		this.trialRegEvent = trialRegEvent;
	}
	
	@OneToMany(mappedBy="trialMemberRegistration",cascade=CascadeType.ALL)
	public Set<RefreeMarksMatch> getRefreeMarksMatch() {
		return refreeMarksMatch;
	}
	public void setRefreeMarksMatch(Set<RefreeMarksMatch> refreeMarksMatch) {
		this.refreeMarksMatch = refreeMarksMatch;
	}
	
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getBatch() {
		return Batch;
	}
	public void setBatch(String batch) {
		Batch = batch;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getDummy_id() {
		return dummy_id;
	}
	public void setDummy_id(long dummy_id) {
		this.dummy_id = dummy_id;
	}
	
	
	
}
