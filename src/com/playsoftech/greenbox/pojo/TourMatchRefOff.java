package com.playsoftech.greenbox.pojo;

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

@Entity
@Table(name="tourmatchrefoff")
public class TourMatchRefOff {
	private TourMatchRefOffId tourmatchrefoffId;
	private Gender gender;
	private Tournament tournament;
	private Member member1;
	private Member member2;
	private Login login;
	private Wmatch wmatch;
	
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride( name = "mid",
							column = @Column(name = "mid",nullable = false)),
		@AttributeOverride( name = "tourId",
							column = @Column(name = "tourId",nullable = false))
	})
	public TourMatchRefOffId getTourmatchrefoffId() {
		return tourmatchrefoffId;
	}
	public void setTourmatchrefoffId(TourMatchRefOffId tourmatchrefoffId) {
		this.tourmatchrefoffId = tourmatchrefoffId;
	}
	
	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
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
	@JoinColumn(name = "barcodeId1",nullable = false,insertable = false,updatable = false)
	public Member getMember1() {
		return member1;
	}
	public void setMember1(Member member1) {
		this.member1 = member1;
	}
	
	@ManyToOne
	@JoinColumn(name = "barcodeId2",nullable = false,insertable = false,updatable = false)
	public Member getMember2() {
		return member2;
	}
	public void setMember2(Member member2) {
		this.member2 = member2;
	}
	
	@ManyToOne
	@JoinColumn(name = "login_id",nullable = false,insertable = false,updatable = false)
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
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
	
	
}
