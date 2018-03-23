package com.playsoftech.greenbox.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class WmatchId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long mid;
	private long tourId;
	
	@Column(nullable = false)
	public long getMid() {
		return mid;
	}
	public void setMid(long mid) {
		this.mid = mid;
	}
	@Column(nullable = false)
	public long getTourId() {
		return tourId;
	}
	public void setTourId(long tourId) {
		this.tourId = tourId;
	}
	
	
	
}
