package com.playsoftech.greenbox.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.HibernateException;

import com.playsoftech.greenbox.dao.AddTrialsDAO;
import com.playsoftech.greenbox.dao.GetDAOList;
import com.playsoftech.greenbox.dao.TrialsmemregDAO;
import com.playsoftech.greenbox.pojo.Gender;
import com.playsoftech.greenbox.pojo.MemCategory;
import com.playsoftech.greenbox.pojo.MemStatus;
import com.playsoftech.greenbox.pojo.Member;

import playsoftech.gb.getmyPath;

/**
 * Servlet implementation class TrialsmemregOP
 */
@WebServlet("/TrialsmemregOP")
public class TrialsmemregOP extends HttpServlet {
	private static final long serialVersionUID = 1L;
  public TrialsmemregOP() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try {
			
			/*Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			long year = c.get(Calendar.YEAR);
*/
			long barcode_id = getmyPath.getNewBarcodeId();
			if (!new File(getmyPath.getTrialMemberPath()).exists()) {
				Runtime.getRuntime().exec("mkdir " + getmyPath.getTrialMemberPath());
				//System.out.println("only trialmember");
			}
			if (!new File(getmyPath.getTrialMemberPath() + "season" + new AddTrialsDAO().getmaxTrialsSeasonInfoID()).exists()) {
				Runtime.getRuntime().exec("mkdir " + getmyPath.getTrialMemberPath() + "season" + new AddTrialsDAO().getmaxTrialsSeasonInfoID());
				//System.out.println("trial member with trialyear");
			}

			
			try
			{
				/*System.out.println("trial id : "+request.getParameter("trial_id"));
				System.out.println("reg id : "+request.getParameter("reg_id"));
				System.out.println("fname : "+request.getParameter("first_name"));
				System.out.println("mname : "+request.getParameter("middle_name"));
				System.out.println("lname : "+request.getParameter("last_name"));
				System.out.println("gender : "+request.getParameter("gender"));
				System.out.println("category : "+request.getParameter("category"));*/
				//System.out.println("dob : "+request.getParameter("dob"));
				
				String couple[]=request.getParameter("gender").split("@");
				
				long barcodeId=Long.parseLong(couple[1]);
				
				Gender gender=null;
				if(couple[0].equalsIgnoreCase("male"))
					gender=Gender.MALE;
				else
					gender=Gender.FEMALE;
				
				MemCategory memcategory=null;
				if(request.getParameter("category").equalsIgnoreCase("player"))
					memcategory=MemCategory.PLAYER;
				else
					memcategory=MemCategory.OFFICIAL;
				
				Date dob=new SimpleDateFormat("yyyy/MM/dd").parse(request.getParameter("dob"));
				
				String contact = request.getParameter("contact");
				String addr = request.getParameter("address");
				String email = request.getParameter("email");
				String position = request.getParameter("position");
				 //new TrialsmemregDAO().addNewMember(tmr_id, 	                  barcode_id, fname, 	   mname, 	  lname, 	  dob, 		  gender, photo, trial_id, memcategory)
			String f="";
			List<?> memList = new GetDAOList().getOldMember(barcodeId);
			Iterator<?> iterator = memList.iterator();
			if (iterator.hasNext()) {
				Member member = (Member) iterator.next();
				f="/home/pradeep/Documents/Mark-4/Greenbox2018/WebContent/images/trialmember/season"+new AddTrialsDAO().getmaxTrialsSeasonInfoID()+"/"+barcode_id+".png";
			}
				  if(new TrialsmemregDAO().addNewMember(new TrialsmemregDAO().getmaxtmrID(), barcode_id, request.getParameter("first_name"), request.getParameter("middle_name"), request.getParameter("last_name"), dob, gender, f, Long.parseLong(request.getParameter("trial_id")), memcategory,MemStatus.OLD,addr,position,email,"Batch B- Dec 16-17, 2017",contact))
				//if(new TrialsmemregDAO().addNewMember(new TrialsmemregDAO().getmaxtmrID(), barcodeId, request.getParameter("first_name"), request.getParameter("middle_name"), request.getParameter("last_name"), dob, gender, f, Long.parseLong(request.getParameter("trial_id")), memcategory,MemStatus.OLD,addr,position,email,"Batch B- Dec 16-17, 2017",contact))
				{
					PrintWriter out=response.getWriter();
					out.println("<script type='text/javascript'>alert('Inserted Successfully!!!!');window.location.replace('Trials/AddTrialsmemreg.jsp');</script>");
				}
			
			}
			catch(HibernateException hb)
			{
				System.out.print("Error in TrialsmemregNP adding db : "+hb);
				PrintWriter out=response.getWriter();
				out.println("<script type='text/javascript'>alert('You are doing something wrong.');window.location.replace('Trials/AddTrialsmemreg.jsp');</script>");
			}
			
			
		} catch (Exception e) {
			System.out.println("Error in TrialsmemregNP servlet : " + e);
			PrintWriter out=response.getWriter();
			out.println("<script type='text/javascript'>alert('You are doing something wrong.');window.location.replace('Trials/AddTrialsmemreg.jsp');</script>");
		}
	}

}
