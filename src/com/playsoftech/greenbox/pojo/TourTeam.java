package com.playsoftech.greenbox.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="tourTeam")
public class TourTeam {

	private long ttId;
	private Gender ttGender;
	private Tournament tournament;
	private Team team;
	
	private Set<TourPool> tourpool;
	private Set<TourPlayer> tourplayer;
	private Set<TourCoach> tourcoach;
	private Set<TourManager> tourmanager;
	private Set<Wmatch> wmatch1;
	private Set<Wmatch> wmatch2;
	
	private Set<Wmatch1> wmatch11;
	private Set<Wmatch1> wmatch12;
	private Set<ManoftheMatch> manofthematch;
	private Set<TourGoal> tourgoal;
	private Set<TourFoul> tourfoul;
	private Set<TourCard> tourcard;
	
	private Login login;
	
	@Id
	public long getTtId() {
		return ttId;
	}
	public void setTtId(long ttId) {
		this.ttId = ttId;
	}
	
	
	@Enumerated(EnumType.STRING)
	public Gender getTtGender() {
		return ttGender;
	}
	public void setTtGender(Gender ttGender) {
		this.ttGender = ttGender;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="tourId",nullable=false)
	public Tournament getTournament() {
		return tournament;
	}
	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}
	
	@ManyToOne
	@JoinColumn(name="tId",nullable=false)
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	
	@OneToMany(mappedBy="tourteam",cascade=CascadeType.ALL)
	public Set<TourPool> getTourpool() {
		return tourpool;
	}
	public void setTourpool(Set<TourPool> tourpool) {
		this.tourpool = tourpool;
	}
	
	@OneToMany(mappedBy="tourteam",cascade=CascadeType.ALL)
	public Set<TourPlayer> getTourplayer() {
		return tourplayer;
	}
	public void setTourplayer(Set<TourPlayer> tourplayer) {
		this.tourplayer = tourplayer;
	}
	@OneToMany(mappedBy="tourteam",cascade=CascadeType.ALL)
	public Set<TourCoach> getTourcoach() {
		return tourcoach;
	}
	public void setTourcoach(Set<TourCoach> tourcoach) {
		this.tourcoach = tourcoach;
	}
	
	@OneToMany(mappedBy="tourteam",cascade=CascadeType.ALL)
	public Set<TourManager> getTourmanager() {
		return tourmanager;
	}
	public void setTourmanager(Set<TourManager> tourmanager) {
		this.tourmanager = tourmanager;
	}
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="login_id",nullable=false)
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	
	@OneToMany(mappedBy="tourTeam1",cascade = CascadeType.ALL)
	public Set<Wmatch> getWmatch1() {
		return wmatch1;
	}
	public void setWmatch1(Set<Wmatch> wmatch1) {
		this.wmatch1 = wmatch1;
	}
	
	@OneToMany(mappedBy="tourTeam2",cascade = CascadeType.ALL)
	public Set<Wmatch> getWmatch2() {
		return wmatch2;
	}
	public void setWmatch2(Set<Wmatch> wmatch2) {
		this.wmatch2 = wmatch2;
	}
	
	@OneToMany(mappedBy="tourTeam1",cascade = CascadeType.ALL)
	public Set<Wmatch1> getWmatch11() {
		return wmatch11;
	}
	public void setWmatch11(Set<Wmatch1> wmatch11) {
		this.wmatch11 = wmatch11;
	}
	
	@OneToMany(mappedBy="tourTeam2",cascade = CascadeType.ALL)
	public Set<Wmatch1> getWmatch12() {
		return wmatch12;
	}
	public void setWmatch12(Set<Wmatch1> wmatch12) {
		this.wmatch12 = wmatch12;
	}
	
	@OneToMany(mappedBy="tourteam",cascade = CascadeType.ALL)
	public Set<ManoftheMatch> getManofthematch() {
		return manofthematch;
	}
	public void setManofthematch(Set<ManoftheMatch> manofthematch) {
		this.manofthematch = manofthematch;
	}
	
	@OneToMany(mappedBy="tourteam",cascade = CascadeType.ALL)
	public Set<TourGoal> getTourgoal() {
		return tourgoal;
	}
	public void setTourgoal(Set<TourGoal> tourgoal) {
		this.tourgoal = tourgoal;
	}
	@OneToMany(mappedBy="tourteam",cascade=CascadeType.ALL)
	public Set<TourFoul> getTourfoul() {
		return tourfoul;
	}
	public void setTourfoul(Set<TourFoul> tourfoul) {
		this.tourfoul = tourfoul;
	}
	@OneToMany(mappedBy="tourteam",fetch=FetchType.LAZY)
	public Set<TourCard> getTourcard() {
		return tourcard;
	}
	public void setTourcard(Set<TourCard> tourcard) {
		this.tourcard = tourcard;
	}
	
}
