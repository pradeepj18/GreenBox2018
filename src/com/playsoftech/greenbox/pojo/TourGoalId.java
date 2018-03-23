package com.playsoftech.greenbox.pojo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TourGoalId implements Serializable{

	private static final long serialVersionUID = 1L;

	private long gno;
	private long tourId;
	
	public long getGno() {
		return gno;
	}
	public void setGno(long gno) {
		this.gno = gno;
	}
	public long getTourId() {
		return tourId;
	}
	public void setTourId(long tourId) {
		this.tourId = tourId;
	}
	 
}
