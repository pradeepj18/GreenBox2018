package com.playsoftech.greenbox.pojo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TourPlayerId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long tourId;
	private long barcodeId;
	private long ttId;
	
	
	public long getTourId() {
		return tourId;
	}
	public void setTourId(long tourId) {
		this.tourId = tourId;
	}
	public long getBarcodeId() {
		return barcodeId;
	}
	public void setBarcodeId(long barcodeId) {
		this.barcodeId = barcodeId;
	}
	public long getTtId() {
		return ttId;
	}
	public void setTtId(long ttId) {
		this.ttId = ttId;
	}
	
	
}
