package com.playsoftech.greenbox.pojo;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="wmatch1")
public class Wmatch1 {

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
	@JoinColumn(name = "tourId",nullable = false,updatable=false,insertable=false)
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
	
	
}
