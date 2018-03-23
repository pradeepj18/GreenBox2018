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
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.HibernateException;

import com.playsoftech.greenbox.dao.AddTrialsDAO;
import com.playsoftech.greenbox.dao.TrialsmemregDAO;
import com.playsoftech.greenbox.pojo.Gender;
import com.playsoftech.greenbox.pojo.MemCategory;
import com.playsoftech.greenbox.pojo.MemStatus;

import playsoftech.gb.*;

@WebServlet("/TrialsmemregNP")
public class TrialsmemregNP extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TrialsmemregNP() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try {
			boolean success=false;
			//long barcode_id = new TrialsmemregDAO().getmaxID();
			long barcode_id = getmyPath.getNewBarcodeId();
			/*Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			long year = c.get(Calendar.YEAR);*/

			if (!new File(getmyPath.getTrialMemberPath()).exists()) {
				Runtime.getRuntime().exec("mkdir " + getmyPath.getTrialMemberPath());
				//System.out.println("only trialmember");
			}
			if (!new File(getmyPath.getTrialMemberPath() + "season" + new AddTrialsDAO().getmaxTrialsSeasonInfoID()).exists()) {
				Runtime.getRuntime().exec("mkdir " + getmyPath.getTrialMemberPath() + "season" + new AddTrialsDAO().getmaxTrialsSeasonInfoID());
				//System.out.println("trial member with trialyear");
			}

			/*if (barcode_id == 1)
				barcode_id = (year * 10000) + 1001;
			else {
				barcode_id = Long.parseLong(Long.toString(barcode_id).substring(4, Long.toString(barcode_id).length()));
				barcode_id = (year * 10000) + barcode_id + 1;
			}*/

			String ext = "";
			FileItem item = null;
			String Fvalues[] = new String[20];// for form values
			int cnt = 0;
			String Bvalues[] = new String[2];// for browse values
			int cnt1 = 0;
			String f = null;

			String ImageFile = "";
			String itemName = "";

			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (!isMultipart) {
			} else {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<?> items = null;
				try {
					items = upload.parseRequest(request);
				} catch (FileUploadException e) {
					e.getMessage();
				}

				Iterator<?> itr = items.iterator();
				while (itr.hasNext()) {
					item = (FileItem) itr.next();
					if (item.isFormField()) {
						String name = item.getFieldName();
						String value = item.getString();
						Fvalues[cnt++] = value;
						if (name.equals("pphoto")) {
							ImageFile = value;
						}

					} else {

						Bvalues[cnt1] = item.getName();
						ext = Bvalues[cnt1].substring(Bvalues[cnt1].lastIndexOf("."));
						f = getmyPath.getTrialMemberPath() + "season" + new AddTrialsDAO().getmaxTrialsSeasonInfoID()+"/"+ barcode_id + ext;
						try
						{
							/*System.out.println("trial id : "+Fvalues[0]);
							System.out.println("reg id : "+Fvalues[1]);
							System.out.println("fname : "+Fvalues[2]);
							System.out.println("mname : "+Fvalues[3]);
							System.out.println("lname : "+Fvalues[4]);
							System.out.println("gender : "+Fvalues[5]);
							System.out.println("category : "+Fvalues[6]);*/
							//System.out.println("dob : "+Fvalues[7]);
							
							Gender gender=null;
							if(Fvalues[5].equalsIgnoreCase("male"))
								gender=Gender.MALE;
							else
								gender=Gender.FEMALE;
							
							MemCategory memcategory=null;
							if(Fvalues[6].equalsIgnoreCase("player"))
								memcategory=MemCategory.PLAYER;
							else
								memcategory=MemCategory.OFFICIAL;
							
							Date dob=new SimpleDateFormat("yyyy/MM/dd").parse(Fvalues[7]);
							String contact = Fvalues[1];
							String addr = Fvalues[8];
							String email = Fvalues[9];
							String position = Fvalues[10];
							
							//											(long tmr_id,long barcode_id,String fname,String mname,String lname,Date dob,Gender gender,String photo,long trial_id,MemCategory memcategory,MemStatus memstatus,String address,String position,String email,String batch,String mobile) {
							if(ext.equalsIgnoreCase(".jpg") || ext.equalsIgnoreCase(".jpeg") || ext.equalsIgnoreCase(".png"))
							{	
								if(new TrialsmemregDAO().addNewMember(new TrialsmemregDAO().getmaxtmrID(), barcode_id, Fvalues[2], Fvalues[3], Fvalues[4], dob, gender, f, Long.parseLong(Fvalues[0]), memcategory,MemStatus.NEW,addr,position,email,"Batch B- Dec 16-17, 2017",contact))
								{
									File savedFile = new File(f);
									item.write(savedFile);
									cnt1++;
									success=true;
								}
							
								if(success)
								{
									PrintWriter out=response.getWriter();
									out.println("<script type='text/javascript'>alert('Inserted Successfully!!!!');window.location.replace('Trials/AddTrialsmemreg.jsp');</script>");
									
								}
							}
							else
							{
								PrintWriter out=response.getWriter();
								out.println("<script type='text/javascript'>alert('You are doing something wrong.');window.location.replace('Trials/AddTrialsmemreg.jsp');</script>");
							}
						}
						catch(HibernateException hb)
						{
							System.out.print("Error in TrialsmemregNP adding db : "+hb);
							PrintWriter out=response.getWriter();
							out.println("<script type='text/javascript'>alert('You are doing something wrong.');window.location.replace('Trials/AddTrialsmemreg.jsp');</script>");
						}
						
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println("Error in TrialsmemregNP servlet : " + e);
			PrintWriter out=response.getWriter();
			out.println("<script type='text/javascript'>alert('You are doing something wrong.');window.location.replace('Trials/AddTrialsmemreg.jsp');</script>");
		}
	}

}
