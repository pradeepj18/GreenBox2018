package com.playsoftech.greenbox.pojo;

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
import javax.persistence.Table;

@Entity
@Table(name="tourPlayer")
public class TourPlayer {
	
	private long jerseyNo;
	private PlayingStatus playingstatus;
	private Tournament tournament;
	private TourTeam tourteam;
	private Member member;
	private PlayerCategory playercategory;
	
	private TourPlayerId tourPlayerId;
	
	private Login login;

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride( name = "ttId",
							column = @Column(name = "ttId",nullable = false)),
		@AttributeOverride( name = "barcodeId",
							column = @Column(name = "barcodeId",nullable = false)),
		@AttributeOverride( name = "tourId",
							column = @Column(name = "tourId" ,nullable = false))
	})
	public TourPlayerId getTourPlayerId() {
		return tourPlayerId;
	}

	public void setTourPlayerId(TourPlayerId tourPlayerId) {
		this.tourPlayerId = tourPlayerId;
	}

	public long getJerseyNo() {
		return jerseyNo;
	}

	public void setJerseyNo(long jerseyNo) {
		this.jerseyNo = jerseyNo;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="pStatus")
	public PlayingStatus getPlayingstatus() {
		return playingstatus;
	}

	public void setPlayingstatus(PlayingStatus playingstatus) {
		this.playingstatus = playingstatus;
	}
	
	
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="tourId",nullable=false,insertable=false,updatable=false)
	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "ttId",nullable = false,insertable=false,updatable=false)
	public TourTeam getTourteam() {
		return tourteam;
	}

	public void setTourteam(TourTeam tourteam) {
		this.tourteam = tourteam;
	}

	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="barcodeId",nullable=false,insertable=false,updatable=false)
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="category")
	public PlayerCategory getPlayercategory() {
		return playercategory;
	}

	public void setPlayercategory(PlayerCategory playercategory) {
		this.playercategory = playercategory;
	}

	
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="login_id",nullable=false)
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}

	
	
	
	
}
