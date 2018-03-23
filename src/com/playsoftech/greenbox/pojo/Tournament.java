package com.playsoftech.greenbox.pojo;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author pradeep
 *
 */
@Entity
@Table(name="tournament")
public class Tournament {
	private long tourId;
	private String tourName;
	private String tourTitle;
	private String tourVenue;
	private String tourOrgBy;
	private Date tourSDate;
	private Date tourEDate;
	private String tourLogo;
	private TourAge tourAge;
	
	private Set<TourTeam> tourteam;
	private Set<TourPool> tourpool;
	private Set<TourPlayer> tourplayer;
	private Set<TourCoach> tourcoach;
	private Set<TourManager> tourmanager;
	private Set<TourOfficial> tourofficial;
	private Set<Wmatch> wmatch;
	private Set<Wmatch1> wmatch1;
	private Set<ManoftheMatch> manofthematch;
	private Set<TourMatchRefOff> tourmatchrefoff;
	private Set<TourGoal> tourgoal;
	private Set<TourFoul> tourfoul;
	private Set<TourCard> tourcard;
	private Set<ImageGallery> imagegallery;
	private Set<VideoGallery> videogallery;
	
	
	private Login login;
	
	@Id
	public long getTourId() {
		return tourId;
	}
	public void setTourId(long tourId) {
		this.tourId = tourId;
	}
	public String getTourName() {
		return tourName;
	}
	public void setTourName(String tourName) {
		this.tourName = tourName;
	}
	public String getTourTitle() {
		return tourTitle;
	}
	public void setTourTitle(String tourTitle) {
		this.tourTitle = tourTitle;
	}
	public String getTourVenue() {
		return tourVenue;
	}
	public void setTourVenue(String tourVenue) {
		this.tourVenue = tourVenue;
	}
	public String getTourOrgBy() {
		return tourOrgBy;
	}
	public void setTourOrgBy(String tourOrgBy) {
		this.tourOrgBy = tourOrgBy;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getTourSDate() {
		return tourSDate;
	}
	public void setTourSDate(Date tourSDate) {
		this.tourSDate = tourSDate;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getTourEDate() {
		return tourEDate;
	}
	public void setTourEDate(Date tourEDate) {
		this.tourEDate = tourEDate;
	}
	public String getTourLogo() {
		return tourLogo;
	}
	public void setTourLogo(String tourLogo) {
		this.tourLogo = tourLogo;
	}
	
	@Enumerated(EnumType.STRING)
	public TourAge getTourAge() {
		return tourAge;
	}
	public void setTourAge(TourAge tourAge) {
		this.tourAge = tourAge;
	}
	
	@OneToMany(mappedBy="tournament" ,cascade=CascadeType.ALL)
	public Set<TourTeam> getTourteam() {
		return tourteam;
	}
	public void setTourteam(Set<TourTeam> tourteam) {
		this.tourteam = tourteam;
	}
	@OneToMany(mappedBy="tournament" ,cascade=CascadeType.ALL)
	public Set<TourPool> getTourpool() {
		return tourpool;
	}
	public void setTourpool(Set<TourPool> tourpool) {
		this.tourpool = tourpool;
	}
	
	@OneToMany(mappedBy="tournament" ,cascade=CascadeType.ALL)
	public Set<TourPlayer> getTourplayer() {
		return tourplayer;
	}
	public void setTourplayer(Set<TourPlayer> tourplayer) {
		this.tourplayer = tourplayer;
	}
	@OneToMany(mappedBy="tournament" ,cascade=CascadeType.ALL)
	public Set<TourCoach> getTourcoach() {
		return tourcoach;
	}
	public void setTourcoach(Set<TourCoach> tourcoach) {
		this.tourcoach = tourcoach;
	}
	
	@OneToMany(mappedBy="tournament" ,cascade=CascadeType.ALL)
	public Set<TourManager> getTourmanager() {
		return tourmanager;
	}
	public void setTourmanager(Set<TourManager> tourmanager) {
		this.tourmanager = tourmanager;
	}
	
	@OneToMany(mappedBy="tournament" ,cascade=CascadeType.ALL)
	public Set<TourOfficial> getTourofficial() {
		return tourofficial;
	}
	public void setTourofficial(Set<TourOfficial> tourofficial) {
		this.tourofficial = tourofficial;
	}
	
	@ManyToOne
	@JoinColumn(name="login_id",nullable=false)
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	
	@OneToMany(mappedBy="tournament" ,cascade=CascadeType.ALL)
	public Set<Wmatch> getWmatch() {
		return wmatch;
	}
	public void setWmatch(Set<Wmatch> wmatch) {
		this.wmatch = wmatch;
	}
	
	@OneToMany(mappedBy="tournament" ,cascade=CascadeType.ALL)
	public Set<Wmatch1> getWmatch1() {
		return wmatch1;
	}
	public void setWmatch1(Set<Wmatch1> wmatch1) {
		this.wmatch1 = wmatch1;
	}
	
	@OneToMany(mappedBy="tournament" ,cascade=CascadeType.ALL)
	public Set<ManoftheMatch> getManofthematch() {
		return manofthematch;
	}
	public void setManofthematch(Set<ManoftheMatch> manofthematch) {
		this.manofthematch = manofthematch;
	}
	
	@OneToMany(mappedBy="tournament" ,cascade=CascadeType.ALL)
	public Set<TourMatchRefOff> getTourmatchrefoff() {
		return tourmatchrefoff;
	}
	public void setTourmatchrefoff(Set<TourMatchRefOff> tourmatchrefoff) {
		this.tourmatchrefoff = tourmatchrefoff;
	}
	
	@OneToMany(mappedBy="tournament" ,cascade=CascadeType.ALL)
	public Set<TourGoal> getTourgoal() {
		return tourgoal;
	}
	public void setTourgoal(Set<TourGoal> tourgoal) {
		this.tourgoal = tourgoal;
	}
	@OneToMany(mappedBy="tournament" ,cascade=CascadeType.ALL)
	public Set<TourFoul> getTourfoul() {
		return tourfoul;
	}
	public void setTourfoul(Set<TourFoul> tourfoul) {
		this.tourfoul = tourfoul;
	}
	@OneToMany(mappedBy="tournament",fetch=FetchType.LAZY)
	public Set<TourCard> getTourcard() {
		return tourcard;
	}
	public void setTourcard(Set<TourCard> tourcard) {
		this.tourcard = tourcard;
	}
	@OneToMany(mappedBy="tournament",fetch=FetchType.LAZY)
	public Set<ImageGallery> getImagegallery() {
		return imagegallery;
	}
	public void setImagegallery(Set<ImageGallery> imagegallery) {
		this.imagegallery = imagegallery;
	}
	
	@OneToMany(mappedBy="tournament",fetch=FetchType.LAZY)
	public Set<VideoGallery> getVideogallery() {
		return videogallery;
	}
	public void setVideogallery(Set<VideoGallery> videogallery) {
		this.videogallery = videogallery;
	}
	
}
