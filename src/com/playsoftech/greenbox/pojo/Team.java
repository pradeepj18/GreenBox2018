package com.playsoftech.greenbox.pojo;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="team")
public class Team {
	private long tId;
	private String tName;
	private Date tRegDate;
	private TeamStatus teamStatus;
	private String tFlag;
	private String details;
	
	private Set<TourTeam> tourteam;
	private Login login;
	
	@Id
	public long gettId() {
		return tId;
	}
	public void settId(long tId) {
		this.tId = tId;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	
	@Temporal(TemporalType.DATE)
	public Date gettRegDate() {
		return tRegDate;
	}
	public void settRegDate(Date tRegDate) {
		this.tRegDate = tRegDate;
	}
	
	@Enumerated(EnumType.STRING)
	public TeamStatus getTeamStatus() {
		return teamStatus;
	}
	public void setTeamStatus(TeamStatus teamStatus) {
		this.teamStatus = teamStatus;
	}
	public String gettFlag() {
		return tFlag;
	}
	public void settFlag(String tFlag) {
		this.tFlag = tFlag;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	@OneToMany(mappedBy="team",cascade=CascadeType.ALL)
	public Set<TourTeam> getTourteam() {
		return tourteam;
	}
	public void setTourteam(Set<TourTeam> tourteam) {
		this.tourteam = tourteam;
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
