package com.playsoftech.greenbox.pojo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TourOfficialId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long toId;
	private long tourId;
	public long getToId() {
		return toId;
	}
	public void setToId(long toId) {
		this.toId = toId;
	}
	public long getTourId() {
		return tourId;
	}
	public void setTourId(long tourId) {
		this.tourId = tourId;
	}
	
	
}
