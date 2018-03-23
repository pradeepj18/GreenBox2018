package com.playsoftech.greenbox.pojo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TourCMId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long barcodeId;
	private long tourId;
	
	
	public long getBarcodeId() {
		return barcodeId;
	}
	public void setBarcodeId(long barcodeId) {
		this.barcodeId = barcodeId;
	}
	public long getTourId() {
		return tourId;
	}
	public void setTourId(long tourId) {
		this.tourId = tourId;
	}
	
	
}