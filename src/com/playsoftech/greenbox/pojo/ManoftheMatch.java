package com.playsoftech.greenbox.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="manofthematch")
public class ManoftheMatch implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long mmid;
	private Login login;
	private Member member;
	private Tournament tournament;
	private TourTeam tourteam;
	private Wmatch wmatch;
	
	@Id
	public long getMmid() {
		return mmid;
	}
	public void setMmid(long mmid) {
		this.mmid = mmid;
	}
	
	@ManyToOne
	@JoinColumn(name="login_id",nullable=false)
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	
	@ManyToOne
	@JoinColumn(name="barcodeId",nullable=false)
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tourId",nullable=false)
	public Tournament getTournament() {
		return tournament;
	}
	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}
	
	@ManyToOne
	@JoinColumn(name="ttId",nullable=false)
	public TourTeam getTourteam() {
		return tourteam;
	}
	public void setTourteam(TourTeam tourteam) {
		this.tourteam = tourteam;
	}
	
	@MapsId("mid")	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "mid",nullable = false,insertable = false,updatable = false,referencedColumnName = "mid"),
		@JoinColumn(name = "tourId",nullable = false,insertable = false,updatable = false,referencedColumnName = "tourId")
	})
	public Wmatch getWmatch() {
		return wmatch;
	}
	public void setWmatch(Wmatch wmatch) {
		this.wmatch = wmatch;
	}
	
	
}
