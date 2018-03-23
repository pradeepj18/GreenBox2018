package com.playsoftech.greenbox.pojo;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="print_match")
public class PrintMatch {
	private PrintMatchId printMatchId;
	private Date mtime;
	private String twon;
	private int court;
	private Gender gender;
	private long tid1;
	private long tid2;
	private Mtype mtype;
	private long sr_no;
	
	private long login_id;
	
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride( name = "mid",
							column = @Column(name = "mid",nullable = false)),
		@AttributeOverride( name = "tourId",
							column = @Column(name = "tourId",nullable = false)),
		@AttributeOverride( name = "mdate",
							column = @Column(name = "mdate",nullable = false))
	})
	public PrintMatchId getPrintMatchId() {
		return printMatchId;
	}
	public void setPrintMatchId(PrintMatchId printMatchId) {
		this.printMatchId = printMatchId;
	}
	
	@Temporal(TemporalType.TIME)
	public Date getMtime() {
		return mtime;
	}
	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}
	
	public String getTwon() {
		return twon;
	}
	public void setTwon(String twon) {
		this.twon = twon;
	}
	
	public int getCourt() {
		return court;
	}
	public void setCourt(int court) {
		this.court = court;
	}
	
	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public long getTid1() {
		return tid1;
	}
	public void setTid1(long tid1) {
		this.tid1 = tid1;
	}
	public long getTid2() {
		return tid2;
	}
	public void setTid2(long tid2) {
		this.tid2 = tid2;
	}
	
	@Enumerated(EnumType.STRING)
	public Mtype getMtype() {
		return mtype;
	}
	public void setMtype(Mtype mtype) {
		this.mtype = mtype;
	}
	
	public long getSr_no() {
		return sr_no;
	}
	public void setSr_no(long sr_no) {
		this.sr_no = sr_no;
	}
	
	public long getLogin_id() {
		return login_id;
	}
	public void setLogin_id(long login_id) {
		this.login_id = login_id;
	}
	
	
	
}
