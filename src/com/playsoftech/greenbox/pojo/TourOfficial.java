package com.playsoftech.greenbox.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tourOfficial")
public class TourOfficial {
	
	private TourOfficialId tourofficialid;
	private TrialPlayerStatus playingstatus;
	private Tournament tournament;
	private Member member;
	
	private Login login;
	
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride( name = "tourId",
							column = @Column(name = "tourId",nullable = false)),
		@AttributeOverride( name = "toId",
							column = @Column(name = "toId",nullable = false))
	})
	public TourOfficialId getTourofficialid() {
		return tourofficialid;
	}
	public void setTourofficialid(TourOfficialId tourofficialid) {
		this.tourofficialid = tourofficialid;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="pStatus")
	public TrialPlayerStatus getPlayingstatus() {
		return playingstatus;
	}
	public void setPlayingstatus(TrialPlayerStatus playingstatus) {
		this.playingstatus = playingstatus;
	}
	
	@ManyToOne
	@JoinColumn(name="tourId",nullable=false,updatable=false,insertable=false)
	public Tournament getTournament() {
		return tournament;
	}
	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}
	
	@ManyToOne
	@JoinColumn(name="barcodeId",nullable=false,updatable=false,insertable=false)
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
