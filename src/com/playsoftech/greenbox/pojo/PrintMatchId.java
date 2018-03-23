package com.playsoftech.greenbox.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class PrintMatchId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long mid;
	private long tourId;
	private Date mdate;
	
	
	public long getMid() {
		return mid;
	}
	public void setMid(long mid) {
		this.mid = mid;
	}
	public long getTourId() {
		return tourId;
	}
	public void setTourId(long tourId) {
		this.tourId = tourId;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getMdate() {
		return mdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
	
	

}
