package com.playsoftech.greenbox.pojo;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class TrialRefreeLogin {
	private long reglogin_id;
	private String fname;
	private String mname;
	private String lname;
	private String photo;
	private Date dob;
	private Date reg_date;
	private long trial_id;
	private String user_name;
	private String password;
	
	private Set<RefreeMarksMatch> refreeMarksMatch;
	
	@Id
	public long getReglogin_id() {
		return reglogin_id;
	}
	public void setReglogin_id(long reglogin_id) {
		this.reglogin_id = reglogin_id;
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	
	public long getTrial_id() {
		return trial_id;
	}
	public void setTrial_id(long trial_id) {
		this.trial_id = trial_id;
	}
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@OneToMany(mappedBy="refreelogin",cascade=CascadeType.ALL)
	public Set<RefreeMarksMatch> getRefreeMarksMatch() {
		return refreeMarksMatch;
	}
	public void setRefreeMarksMatch(Set<RefreeMarksMatch> refreeMarksMatch) {
		this.refreeMarksMatch = refreeMarksMatch;
	}
	
	
}
