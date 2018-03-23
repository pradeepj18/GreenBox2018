package com.playsoftech.greenbox.pojo;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "member")
public class Member {
	private long barcodeId;
	private String fName;
	private String mName;
	private String lName;
	private Date dob;
	private Gender gender;
	private String photo;
	private String details;
	private MemCategory memCategory;
	private MemStatus memStatus;
	private Date regdate;
	
	private Set<TourPlayer> tourplayer;
	private Set<TourCoach> tourcoach;
	private Set<TourManager> tourmanager;
	private Set<TourOfficial> tourofficial;
	private Set<ManoftheMatch> manofthematch;
	
	private Set<TourMatchRefOff> tourmatchrefoff1;
	private Set<TourMatchRefOff> tourmatchrefoff2;
	
	private Set<TourGoal> tourgoal;
	private Set<TourFoul> tourfoul;
	private Set<TourCard> tourcard;
	
	private Login login;
	
	@Id
	public long getBarcodeId() {
		return barcodeId;
	}
	public void setBarcodeId(long barcodeId) {
		this.barcodeId = barcodeId;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	@Temporal(TemporalType.DATE)
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "category")
	public MemCategory getMemCategory() {
		return memCategory;
	}
	public void setMemCategory(MemCategory memCategory) {
		this.memCategory = memCategory;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	public MemStatus getMemStatus() {
		return memStatus;
	}
	public void setMemStatus(MemStatus memStatus) {
		this.memStatus = memStatus;
	}
	@Temporal(TemporalType.DATE)
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@OneToMany(mappedBy="member",cascade=CascadeType.ALL)
	public Set<TourPlayer> getTourplayer() {
		return tourplayer;
	}
	public void setTourplayer(Set<TourPlayer> tourplayer) {
		this.tourplayer = tourplayer;
	}
	@OneToMany(mappedBy="member",cascade=CascadeType.ALL)
	public Set<TourCoach> getTourcoach() {
		return tourcoach;
	}
	public void setTourcoach(Set<TourCoach> tourcoach) {
		this.tourcoach = tourcoach;
	}
	@OneToMany(mappedBy="member",cascade=CascadeType.ALL)
	public Set<TourManager> getTourmanager() {
		return tourmanager;
	}
	public void setTourmanager(Set<TourManager> tourmanager) {
		this.tourmanager = tourmanager;
	}
	
	@OneToMany(mappedBy="member",cascade=CascadeType.ALL)
	public Set<TourOfficial> getTourofficial() {
		return tourofficial;
	}
	public void setTourofficial(Set<TourOfficial> tourofficial) {
		this.tourofficial = tourofficial;
	}
	
	@ManyToOne
	@JoinColumn(name="login_id",nullable=false)
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	
	@OneToMany(mappedBy="member",cascade=CascadeType.ALL)
	public Set<ManoftheMatch> getManofthematch() {
		return manofthematch;
	}
	public void setManofthematch(Set<ManoftheMatch> manofthematch) {
		this.manofthematch = manofthematch;
	}
	
	@OneToMany(mappedBy="member1",cascade=CascadeType.ALL)
	public Set<TourMatchRefOff> getTourmatchrefoff1() {
		return tourmatchrefoff1;
	}
	public void setTourmatchrefoff1(Set<TourMatchRefOff> tourmatchrefoff1) {
		this.tourmatchrefoff1 = tourmatchrefoff1;
	}
	
	@OneToMany(mappedBy="member2",cascade=CascadeType.ALL)
	public Set<TourMatchRefOff> getTourmatchrefoff2() {
		return tourmatchrefoff2;
	}
	public void setTourmatchrefoff2(Set<TourMatchRefOff> tourmatchrefoff2) {
		this.tourmatchrefoff2 = tourmatchrefoff2;
	}
	
	@OneToMany(mappedBy="member",cascade=CascadeType.ALL)
	public Set<TourGoal> getTourgoal() {
		return tourgoal;
	}
	public void setTourgoal(Set<TourGoal> tourgoal) {
		this.tourgoal = tourgoal;
	}
	@OneToMany(mappedBy="member",cascade=CascadeType.ALL)
	public Set<TourFoul> getTourfoul() {
		return tourfoul;
	}
	public void setTourfoul(Set<TourFoul> tourfoul) {
		this.tourfoul = tourfoul;
	}
	
	@OneToMany(mappedBy="member",fetch=FetchType.LAZY)
	public Set<TourCard> getTourcard() {
		return tourcard;
	}
	public void setTourcard(Set<TourCard> tourcard) {
		this.tourcard = tourcard;
	}

	
}
