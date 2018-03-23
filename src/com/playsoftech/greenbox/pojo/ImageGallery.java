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
public class ImageGallery {
	private long image_id;
	private String image_path;
	private Date image_date;
	private Date image_time;
	private String image_title;
	private Tournament tournament;
	private Login login;
	private long m_id;
	
	@Id
	public long getImage_id() {
		return image_id;
	}
	public void setImage_id(long image_id) {
		this.image_id = image_id;
	}
	
	
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getImage_date() {
		return image_date;
	}
	public void setImage_date(Date image_date) {
		this.image_date = image_date;
	}
	
	@Temporal(TemporalType.TIME)
	public Date getImage_time() {
		return image_time;
	}
	public void setImage_time(Date image_time) {
		this.image_time = image_time;
	}
	
	
	public String getImage_title() {
		return image_title;
	}
	public void setImage_title(String image_title) {
		this.image_title = image_title;
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

	public long getM_id() {
		return m_id;
	}
	public void setM_id(long m_id) {
		this.m_id = m_id;
	}
	
	
	
}
