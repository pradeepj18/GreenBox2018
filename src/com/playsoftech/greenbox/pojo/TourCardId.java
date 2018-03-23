package com.playsoftech.greenbox.pojo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TourCardId implements Serializable{

	private static final long serialVersionUID = 1L;

	private long cno;
	private long tourId;
	
	public long getCno() {
		return cno;
	}
	public void setCno(long cno) {
		this.cno = cno;
	}
	public long getTourId() {
		return tourId;
	}
	public void setTourId(long tourId) {
		this.tourId = tourId;
	}
	 
}
