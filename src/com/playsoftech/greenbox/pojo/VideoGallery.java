package com.playsoftech.greenbox.pojo;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class VideoGallery {
	private long video_id;
	private String video_path;
	private Date video_date;
	private Date video_time;
	private String video_title;
	private Tournament tournament;
	private Login login;
	private long m_id;

	@Id
	public long getVideo_id() {
		return video_id;
	}
	public void setVideo_id(long video_id) {
		this.video_id = video_id;
	}
	
	
	public String getVideo_path() {
		return video_path;
	}
	public void setVideo_path(String video_path) {
		this.video_path = video_path;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getVideo_date() {
		return video_date;
	}
	public void setVideo_date(Date video_date) {
		this.video_date = video_date;
	}
	
	@Temporal(TemporalType.TIME)
	public Date getVideo_time() {
		return video_time;
	}
	public void setVideo_time(Date video_time) {
		this.video_time = video_time;
	}
	
	
	public String getVideo_title() {
		return video_title;
	}
	public void setVideo_title(String video_title) {
		this.video_title = video_title;
	}

	@ManyToOne
	@JoinColumn(name="tour_id",nullable=false)
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
	
	public long getM_id() {
		return m_id;
	}
	public void setM_id(long m_id) {
		this.m_id = m_id;
	}
	
	
	
}
