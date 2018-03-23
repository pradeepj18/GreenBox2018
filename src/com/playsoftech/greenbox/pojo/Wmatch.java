package com.playsoftech.greenbox.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="wmatch")
public class Wmatch implements Serializable{

	private static final long serialVersionUID = 1L;
	private Login login;
	private WmatchId wmatchId;
	private int pool;
	private Tournament tournament;	
	private TourTeam tourTeam1;
	private TourTeam tourTeam2;
	private Gender gender;
	private Mtype mtype;
	private Date time;
	private MatchStatus flag;
	
	private Set<ManoftheMatch> manofthematch;
	private Set<TourMatchRefOff> tourmatchrefoff;
	private Set<TourGoal> tourgoal;
	private Set<TourFoul> tourfoul;
	private Set<TourCard> tourcard;
	
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride( name = "mid",
							column = @Column(name = "mid" ,nullable = false)),
		@AttributeOverride( name = "tourId",
							column = @Column(name = "tourId",nullable = false))
	})
	public WmatchId getWmatchId() {
		return wmatchId;
	}
	public void setWmatchId(WmatchId wmatchId) {
		this.wmatchId = wmatchId;
	}
	
	
	@Column
	public int getPool() {
		return pool;
	}
	public void setPool(int pool) {
		this.pool = pool;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "login_id",nullable = false)
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tourId",nullable = false, insertable = false,updatable = false)
	public Tournament getTournament() {
		return tournament;
	}
	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tid1",nullable = false)
	public TourTeam getTourTeam1() {
		return tourTeam1;
	}
	public void setTourTeam1(TourTeam tourTeam1) {
		this.tourTeam1 = tourTeam1;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tid2",nullable = false)
	public TourTeam getTourTeam2() {
		return tourTeam2;
	}
	public void setTourTeam2(TourTeam tourTeam2) {
		this.tourTeam2 = tourTeam2;
	}
	
	@Enumerated(EnumType.STRING)
	@Column
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	@Enumerated(EnumType.STRING)
	@Column
	public Mtype getMtype() {
		return mtype;
	}
	public void setMtype(Mtype mtype) {
		this.mtype = mtype;
	}
	
	@Temporal(TemporalType.TIME)
	@Column
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	@Enumerated(EnumType.STRING)
	public MatchStatus getFlag() {
		return flag;
	}
	public void setFlag(MatchStatus flag) {
		this.flag = flag;
	}
	
	@OneToMany(mappedBy="wmatch",fetch=FetchType.LAZY)
	public Set<ManoftheMatch> getManofthematch() {
		return manofthematch;
	}
	public void setManofthematch(Set<ManoftheMatch> manofthematch) {
		this.manofthematch = manofthematch;
	}
	
	@OneToMany(mappedBy="wmatch",fetch=FetchType.LAZY)
	public Set<TourMatchRefOff> getTourmatchrefoff() {
		return tourmatchrefoff;
	}
	public void setTourmatchrefoff(Set<TourMatchRefOff> tourmatchrefoff) {
		this.tourmatchrefoff = tourmatchrefoff;
	}
	
	@OneToMany(mappedBy="wmatch",fetch=FetchType.LAZY)
	public Set<TourGoal> getTourgoal() {
		return tourgoal;
	}
	public void setTourgoal(Set<TourGoal> tourgoal) {
		this.tourgoal = tourgoal;
	}
	
	@OneToMany(mappedBy="wmatch",cascade=CascadeType.ALL)
	public Set<TourFoul> getTourfoul() {
		return tourfoul;
	}
	public void setTourfoul(Set<TourFoul> tourfoul) {
		this.tourfoul = tourfoul;
	}
	@OneToMany(mappedBy="wmatch",fetch=FetchType.LAZY)
	public Set<TourCard> getTourcard() {
		return tourcard;
	}
	public void setTourcard(Set<TourCard> tourcard) {
		this.tourcard = tourcard;
	}

}
