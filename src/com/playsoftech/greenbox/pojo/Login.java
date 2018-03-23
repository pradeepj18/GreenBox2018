package com.playsoftech.greenbox.pojo;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Login")
public class Login {

	private long loginid;
	private String username;
	private String password;
	private String emailid;
	private String photo;
	private String mobileno;
	private long tour_id;
	private long trial_id;
	private Admin admin;
	private Date dob;
	private String address;
	private Gender gender;
	private String fname;
	private String lname;
	private Set<Member> member;
	private Set<Tournament> tournament;
	private Set<Team> team;
	private Set<TourTeam> tourteam;
	private Set<TourPlayer> tourplayer;
	private Set<TourPool> tourpool;
	private Set<TourCoach> tourcoach;
	private Set<TourManager> tourmanager;
	private Set<TourOfficial> tourofficial;
	private Set<Wmatch> wmatch;
	private Set<Wmatch1> wmatch1;
	private Set<ManoftheMatch> manofthematch;
	private Set<TourMatchRefOff> tourmtchrefoff;
	private Set<TourGoal> tourgoal;
	private Set<TourFoul> tourfoul;
	private Set<TourCard> tourcard;
	private Set<ImageGallery> imagegallery;
	private Set<VideoGallery> videogallery;
	
	@Id
	@Column(name="login_id")
	public long getLoginid() {
		return loginid;
	}
	public void setLoginid(long loginid) {
		this.loginid = loginid;
	}
	
	@Column(name="user_name")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	
	public long getTour_id() {
		return tour_id;
	}
	public void setTour_id(long tour_id) {
		this.tour_id = tour_id;
	}
	public long getTrial_id() {
		return trial_id;
	}
	public void setTrial_id(long trial_id) {
		this.trial_id = trial_id;
	}
	
	
	@Enumerated(EnumType.STRING)
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	@OneToMany(mappedBy="login",fetch=FetchType.LAZY)
	public Set<Member> getMember() {
		return member;
	}
	public void setMember(Set<Member> member) {
		this.member = member;
	}
	
	@OneToMany(mappedBy="login",fetch=FetchType.LAZY)
	public Set<Tournament> getTournament() {
		return tournament;
	}
	public void setTournament(Set<Tournament> tournament) {
		this.tournament = tournament;
	}
	
	@OneToMany(mappedBy="login",fetch=FetchType.LAZY)
	public Set<Team> getTeam() {
		return team;
	}
	public void setTeam(Set<Team> team) {
		this.team = team;
	}
	@OneToMany(mappedBy="login",fetch=FetchType.LAZY)
	public Set<TourTeam> getTourteam() {
		return tourteam;
	}
	public void setTourteam(Set<TourTeam> tourteam) {
		this.tourteam = tourteam;
	}
	
	@OneToMany(mappedBy="login",fetch=FetchType.LAZY)
	public Set<TourPlayer> getTourplayer() {
		return tourplayer;
	}
	public void setTourplayer(Set<TourPlayer> tourplayer) {
		this.tourplayer = tourplayer;
	}
	@OneToMany(mappedBy="login",fetch=FetchType.LAZY)
	public Set<TourPool> getTourpool() {
		return tourpool;
	}
	public void setTourpool(Set<TourPool> tourpool) {
		this.tourpool = tourpool;
	}
	
	@OneToMany(mappedBy="login",fetch=FetchType.LAZY)
	public Set<TourCoach> getTourcoach() {
		return tourcoach;
	}
	public void setTourcoach(Set<TourCoach> tourcoach) {
		this.tourcoach = tourcoach;
	}
	
	@OneToMany(mappedBy="login",fetch=FetchType.LAZY)
	public Set<TourManager> getTourmanager() {
		return tourmanager;
	}
	public void setTourmanager(Set<TourManager> tourmanager) {
		this.tourmanager = tourmanager;
	}
	
	@OneToMany(mappedBy="login",fetch=FetchType.LAZY)
	public Set<TourOfficial> getTourofficial() {
		return tourofficial;
	}
	public void setTourofficial(Set<TourOfficial> tourofficial) {
		this.tourofficial = tourofficial;
	}
	
	@OneToMany(mappedBy="login",fetch=FetchType.LAZY)
	public Set<Wmatch> getWmatch() {
		return wmatch;
	}
	public void setWmatch(Set<Wmatch> wmatch) {
		this.wmatch = wmatch;
	}
	
	@OneToMany(mappedBy="login",fetch=FetchType.LAZY)
	public Set<Wmatch1> getWmatch1() {
		return wmatch1;
	}
	public void setWmatch1(Set<Wmatch1> wmatch1) {
		this.wmatch1 = wmatch1;
	}
	
	@OneToMany(mappedBy="login",fetch=FetchType.LAZY)
	public Set<ManoftheMatch> getManofthematch() {
		return manofthematch;
	}
	public void setManofthematch(Set<ManoftheMatch> manofthematch) {
		this.manofthematch = manofthematch;
	}
	
	@OneToMany(mappedBy="login",fetch=FetchType.LAZY)
	public Set<TourMatchRefOff> getTourmtchrefoff() {
		return tourmtchrefoff;
	}
	public void setTourmtchrefoff(Set<TourMatchRefOff> tourmtchrefoff) {
		this.tourmtchrefoff = tourmtchrefoff;
	}
	
	@OneToMany(mappedBy="login",fetch=FetchType.LAZY)
	public Set<TourGoal> getTourgoal() {
		return tourgoal;
	}
	public void setTourgoal(Set<TourGoal> tourgoal) {
		this.tourgoal = tourgoal;
	}
	
	@OneToMany(mappedBy="login",fetch=FetchType.LAZY)
	public Set<TourFoul> getTourfoul() {
		return tourfoul;
	}
	public void setTourfoul(Set<TourFoul> tourfoul) {
		this.tourfoul = tourfoul;
	}
	
	@OneToMany(mappedBy="login",fetch=FetchType.LAZY)
	public Set<TourCard> getTourcard() {
		return tourcard;
	}
	public void setTourcard(Set<TourCard> tourcard) {
		this.tourcard = tourcard;
	}
	
	@OneToMany(mappedBy="login",fetch=FetchType.LAZY)
	public Set<ImageGallery> getImagegallery() {
		return imagegallery;
	}
	public void setImagegallery(Set<ImageGallery> imagegallery) {
		this.imagegallery = imagegallery;
	}
	
	@OneToMany(mappedBy="login",fetch=FetchType.LAZY)
	public Set<VideoGallery> getVideogallery() {
		return videogallery;
	}
	public void setVideogallery(Set<VideoGallery> videogallery) {
		this.videogallery = videogallery;
	}
	
	
}
