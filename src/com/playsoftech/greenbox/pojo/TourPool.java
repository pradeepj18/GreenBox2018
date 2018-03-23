package com.playsoftech.greenbox.pojo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tourPool")
public class TourPool {
	
	private long tpId;
	private String pname;
	private TourTeam tourteam;
	private Gender tpgender;
	private Tournament tournament;
	
	private Login login;
	
	@Id
	public long getTpId() {
		return tpId;
	}
	public void setTpId(long tpId) {
		this.tpId = tpId;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ttId",nullable = false)
	public TourTeam getTourteam() {
		return tourteam;
	}
	public void setTourteam(TourTeam tourteam) {
		this.tourteam = tourteam;
	}
	
	
	@Enumerated(EnumType.STRING)
	public Gender getTpgender() {
		return tpgender;
	}
	public void setTpgender(Gender tpgender) {
		this.tpgender = tpgender;
	}
	
	@ManyToOne
	@JoinColumn(name="tourId",nullable=false)
	public Tournament getTournament() {
		return tournament;
	}
	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
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
