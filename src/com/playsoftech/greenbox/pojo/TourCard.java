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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tourCard")
public class TourCard {
	private TourCardId tourcardid;
	private Date ctime;
	private Member member;
	private Tournament tournament;
	private Wmatch wmatch;
	private TourTeam tourteam;
	private CardType ctype;
	private CardName cname;
	
	private Login login;
	
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride( name = "cno",
							column = @Column(name = "cno",nullable = false)),
		@AttributeOverride( name = "tourId",
							column = @Column(name = "tourId",nullable = false))
	})
	public TourCardId getTourcardid() {
		return tourcardid;
	}
	public void setTourcardid(TourCardId tourcardid) {
		this.tourcardid = tourcardid;
	}
	
	@ManyToOne
	@JoinColumn(name = "tourId",nullable = false,insertable = false,updatable = false)
	public Tournament getTournament() {
		return tournament;
	}
	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}
	
	@ManyToOne
	@JoinColumn(name = "barcodeId",nullable = false)
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
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
	
	@ManyToOne
	@JoinColumn(name = "ttId",nullable = false)
	public TourTeam getTourteam() {
		return tourteam;
	}
	public void setTourteam(TourTeam tourteam) {
		this.tourteam = tourteam;
	}
	
	@ManyToOne
	@JoinColumn(name = "login_id",nullable = false)
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	
	@Temporal(TemporalType.TIME)
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
	@Enumerated(EnumType.STRING)
	public CardType getCtype() {
		return ctype;
	}
	public void setCtype(CardType ctype) {
		this.ctype = ctype;
	}
	
	@Enumerated(EnumType.STRING)
	public CardName getCname() {
		return cname;
	}
	public void setCname(CardName cname) {
		this.cname = cname;
	}
	
	
	
}
