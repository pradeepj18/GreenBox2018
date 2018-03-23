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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tourManager")
public class TourManager {
	private TourCMId tourcmid;
	private PlayingStatus playingstatus;
	private Tournament tournament;
	private TourTeam tourteam;
	private Member member;
	
	private Login login;
	
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride( name = "tourId",
							column = @Column(name = "tourId",nullable = false)),
		@AttributeOverride( name = "barcodeId",
							column = @Column(name = "barcodeId",nullable = false))
	})
	public TourCMId getTourcmid() {
		return tourcmid;
	}
	public void setTourcmid(TourCMId tourcmid) {
		this.tourcmid = tourcmid;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="pStatus")
	public PlayingStatus getPlayingstatus() {
		return playingstatus;
	}
	public void setPlayingstatus(PlayingStatus playingstatus) {
		this.playingstatus = playingstatus;
	}
	
	@ManyToOne
	@JoinColumn(name="tourId",nullable=false,insertable = false, updatable = false)
	public Tournament getTournament() {
		return tournament;
	}
	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ttId",nullable = false,insertable = false,updatable = false)
	public TourTeam getTourteam() {
		return tourteam;
	}
	public void setTourteam(TourTeam tourteam) {
		this.tourteam = tourteam;
	}
	
	@ManyToOne
	@JoinColumn(name="barcodeId",nullable=false,insertable = false, updatable = false)
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	@ManyToOne
	@JoinColumn(name="login_id",nullable=false)
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	
}
