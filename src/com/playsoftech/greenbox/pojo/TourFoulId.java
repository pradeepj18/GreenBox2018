package com.playsoftech.greenbox.pojo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TourFoulId implements Serializable{

	private static final long serialVersionUID = 1L;

	private long fno;
	private long tourId;
	
	
	public long getFno() {
		return fno;
	}
	public void setFno(long fno) {
		this.fno = fno;
	}
	public long getTourId() {
		return tourId;
	}
	public void setTourId(long tourId) {
		this.tourId = tourId;
	}
	 
}
