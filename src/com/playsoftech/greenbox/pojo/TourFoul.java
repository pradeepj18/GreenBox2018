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
@Table(name="tourFoul")
public class TourFoul {
	
	private TourFoulId tourfoulid;
	private Date ftime;
	private Tournament tournament;
	private Member member;
	private Wmatch wmatch;
	private TourTeam tourteam;
	private GoalFoulType foultype;
	private Login login;
	
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride( name = "fno",
							column = @Column(name = "fno",nullable = false)),
		@AttributeOverride( name = "tourId",
							column = @Column(name = "tourId",nullable = false))
	})
	public TourFoulId getTourfoulid() {
		return tourfoulid;
	}
	public void setTourfoulid(TourFoulId tourfoulid) {
		this.tourfoulid = tourfoulid;
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
	@JoinColumn(name = "barcodeId",nullable = false,insertable = false,updatable = false)
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
	@JoinColumn(name = "ttId",nullable = false,insertable = false,updatable = false)
	public TourTeam getTourteam() {
		return tourteam;
	}
	public void setTourteam(TourTeam tourteam) {
		this.tourteam = tourteam;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="ftype")
	public GoalFoulType getFoultype() {
		return foultype;
	}
	public void setFoultype(GoalFoulType foultype) {
		this.foultype = foultype;
	}
	
	@ManyToOne
	@JoinColumn(name = "login_id",nullable = false,insertable = false,updatable = false)
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	
	@Temporal(TemporalType.TIME)
	public Date getFtime() {
		return ftime;
	}
	public void setFtime(Date ftime) {
		this.ftime = ftime;
	}
	
	
	
	
}
