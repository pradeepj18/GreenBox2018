package com.playsoftech.greenbox.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Comparator;
import java.util.List;

import org.hibernate.HibernateException;
import com.playsoftech.greenbox.pojo.TrialMemberRegistration;
import playsoftech.gb.getmyPath;

public class TrialMarksEvaluation {
	
	private String fname;
	private String mname;
	private String lname;
	private String age;
	private String position;
	private double match_marks;
	private double hundred_mtrs;
	private double pushup;
	private double r_w;
	private double dribbling;
	private double plank;
	private double total;
	private double shutle_run;
	private double reflex;
	private long tmr_id;
	private String batch;
	
	public TrialMarksEvaluation() {}
	
	public TrialMarksEvaluation(String fname, String mname, String lname, String age,String position, double match_marks, double hundred_mtrs,
			double pushup, double r_w, double dribbling, double plank,double total,double shutle_run,double reflex,long tmr_id,String batch) {
		super();
		this.fname = fname;
		this.mname = mname;
		this.lname = lname;
		this.position = position;
		this.age = age;
		this.match_marks = match_marks;
		this.hundred_mtrs = hundred_mtrs;
		this.pushup = pushup;
		this.r_w = r_w;
		this.dribbling = dribbling;
		this.plank = plank;
		this.total = total;
		this.shutle_run = shutle_run;
		this.reflex = reflex;
		this.tmr_id = tmr_id;
		this.batch = batch;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public double getMatch_marks() {
		return match_marks;
	}

	public void setMatch_marks(double match_marks) {
		this.match_marks = match_marks;
	}

	public double getHundred_mtrs() {
		return hundred_mtrs;
	}

	public void setHundred_mtrs(double hundred_mtrs) {
		this.hundred_mtrs = hundred_mtrs;
	}

	public double getPushup() {
		return pushup;
	}

	public void setPushup(double pushup) {
		this.pushup = pushup;
	}

	public double getR_w() {
		return r_w;
	}

	public void setR_w(double r_w) {
		this.r_w = r_w;
	}

	public double getDribbling() {
		return dribbling;
	}

	public void setDribbling(double dribbling) {
		this.dribbling = dribbling;
	}

	public double getPlank() {
		return plank;
	}

	public void setPlank(double plank) {
		this.plank = plank;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getShutle_run() {
		return shutle_run;
	}

	public void setShutle_run(double shutle_run) {
		this.shutle_run = shutle_run;
	}

	public double getReflex() {
		return reflex;
	}

	public void setReflex(double reflex) {
		this.reflex = reflex;
	}

	public long getTmr_id() {
		return tmr_id;
	}

	public void setTmr_id(long tmr_id) {
		this.tmr_id = tmr_id;
	}

	
	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public List<?> marksEvalute(long trial_id)
	{
		List<TrialMarksEvaluation> playerList = new ArrayList<TrialMarksEvaluation>();
		try
		{
			GetDAOList getdaolist = new GetDAOList();
			GetDAOBool getDAOBool = new GetDAOBool();
			//List<?> memlist = getdaolist.getTrialMemberBatch(trial_id, "Batch B- Dec 16-17, 2017");
			//List<?> memlist = getdaolist.getTrialMemberBatch(trial_id, "Batch A- Dec 09-10, 2017");
			List<?> memlist = getdaolist.getTrialMemberList(trial_id);
			Iterator<?> itr = memlist.iterator();
			TrialMarksEvaluation tmrEval[] = new TrialMarksEvaluation[memlist.size()];
			int i = 0;
			while(itr.hasNext())
			{
				TrialMemberRegistration tmr = (TrialMemberRegistration) itr.next();
				
				double match_marks = getdaolist.getTrialMatchMarks(tmr.getTmr_id(), trial_id);
				
				match_marks = match_marks+(match_marks / 4);
				double hundred_mtr = getdaolist.getEventMarks(trial_id, tmr.getTmr_id(), 1);
				double pushup =  getdaolist.getEventMarks(trial_id, tmr.getTmr_id(), 2);
				double r_w = getdaolist.getEventMarks(trial_id, tmr.getTmr_id(), 3);
				
				double dribbling = getdaolist.getEventMarks(trial_id, tmr.getTmr_id(), 4);
				double plank = getdaolist.getEventMarks(trial_id, tmr.getTmr_id(), 5);
				double shutle = getdaolist.getEventMarks(trial_id, tmr.getTmr_id(), 6);
				double reflex = getdaolist.getEventMarks(trial_id, tmr.getTmr_id(), 7);
				double total = 0.0;
				if(tmr.getPosition().equalsIgnoreCase("goalkeeper"))
				{
					total = match_marks+pushup+r_w+plank+shutle+reflex;
				}
				else
				{
					if(getDAOBool.isDribblingGoal(4, trial_id, tmr.getTmr_id()))
					{
						dribbling = dribbling+1;
						if(dribbling > 10.0)
							dribbling = 10.0;
					}
					/*else
					{
						dribbling = dribbling-1;
						if(dribbling < 0)
							dribbling = 0.0;
					}*/
					total = match_marks+hundred_mtr+pushup+r_w+dribbling+plank;
				}
				tmrEval[i] = new TrialMarksEvaluation(tmr.getFname().toUpperCase(), tmr.getMname().toUpperCase(), tmr.getLname().toUpperCase(), getmyPath.getAge(tmr.getDob()), tmr.getPosition(),match_marks, hundred_mtr, pushup, r_w, dribbling, plank,total,shutle,reflex,tmr.getTmr_id(),tmr.getBatch());
				
				playerList.add(tmrEval[i]);
				i++;
			}
			Collections.sort(playerList,new trialPlayerEval());
		}
		catch(HibernateException e)
		{
			System.out.println("TrialMarksEvalution marksEvalute "+e);
		}
		return playerList;
	}

	public class trialPlayerEval implements Comparator<TrialMarksEvaluation>
	{
		public int compare(TrialMarksEvaluation tmrEval,TrialMarksEvaluation tmrEval1)
		{
			Double marks1 = tmrEval.getTotal();
			Double marks2 = tmrEval1.getTotal();
			return marks2.compareTo(marks1);
		}
	}
	
}
