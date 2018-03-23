package com.playsoftech.greenbox.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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

import com.playsoftech.greenbox.dao.AddTrialsDAO;
import com.playsoftech.greenbox.dao.AddTrialsRefreeDAO;

import playsoftech.gb.getmyPath;

@WebServlet("/AddTrialsRefree")
public class AddTrialsRefree extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			AddTrialsRefreeDAO addTrialsRefree = new AddTrialsRefreeDAO(); 
			long reflogin_id = addTrialsRefree.getmaxTrialsRefreeid()+1;
			/*String imagepath1=getmyPath.getTrialRefreePath();
			File file=new File(imagepath1);*/
			/*if(!new File(imagepath1).exists())
			{
				Runtime.getRuntime().exec("mkdir "+imagepath1);
				
			}*/
			if (!new File(getmyPath.getTrialRefreePath()).exists()) {
				Runtime.getRuntime().exec("mkdir " + getmyPath.getTrialRefreePath());
				//System.out.println("only trialmember");
			}
			
			if (!new File(getmyPath.getTrialRefreePath() + "season" + new AddTrialsDAO().getmaxTrialsSeasonInfoID()).exists()) {
				Runtime.getRuntime().exec("mkdir " + getmyPath.getTrialRefreePath() + "season" + new AddTrialsDAO().getmaxTrialsSeasonInfoID());
				//System.out.println("trial member with trialyear");
			}

			/*
			String id = request.getSession().getAttribute("id").toString();
			long login_id = Long.parseLong(id);
			*/
			String ext="";
			FileItem item = null;
			String Fvalues[] = new String[10];// for form values
			int cnt = 0;
			String Bvalues[] = new String[10];// for browse values
			int cnt1 = 0;
			String f = null;
			try 
			{
				
				String ImageFile = "";
				String itemName = "";

				boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				if (!isMultipart) {
				} 
				else 
				{
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

						} 
						else
						{
						
										Bvalues[cnt1] = item.getName();
										
											ext = Bvalues[cnt1].substring(Bvalues[cnt1].lastIndexOf("."));
											f = getmyPath.getTrialRefreePath() + "season" + new AddTrialsDAO().getmaxTrialsSeasonInfoID()+"/" +Fvalues[0].replaceAll(" ", "")+reflogin_id+ ext;
											if(ext.equalsIgnoreCase(".jpg") || ext.equalsIgnoreCase(".jpeg") || ext.equalsIgnoreCase(".png"))
											{
												File savedFile = new File(f);
												item.write(savedFile);
											}
							
							cnt1++;
						}
					}

				}
			}

			catch (Exception e) {
				System.out.println("Error in servlet AddTrailsRefree AddImage.." + e);
			}
			
			String fname = Fvalues[0];
			String mname = Fvalues[1];
			String lname = Fvalues[2];
			String dob_ref = Fvalues[3];
			long trial_id = Long.parseLong(Fvalues[4]);
			Date dob = new SimpleDateFormat("yyyy/MM/dd").parse(dob_ref);
			Date reg_date=new SimpleDateFormat("yyyy/MM/dd").parse(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
			
			PrintWriter out=response.getWriter();
			if(ext.equalsIgnoreCase(".jpg") || ext.equalsIgnoreCase(".jpeg") || ext.equalsIgnoreCase(".png"))
			{
			
				if(addTrialsRefree.addTrialsRefree(reflogin_id, fname, mname, lname, dob, f, reg_date,trial_id))
				{
					out.println("<script type='text/javascript'>alert('Refree Added Successfully !!!');window.location.replace('Trials/AddTrialsRefree.jsp');</script>");
				}
				else{
					out.println("<script type='text/javascript'>alert('Error in add chech with the field');window.location.replace('Trials/AddTrialsRefree.jsp');</script>");
				}
			}
			else
			{
				out.println("<script type='text/javascript'>alert('Select the correct image');window.location.replace('Trials/AddTrialsRefree.jsp');</script>");
			}
			
			
			
		}
		catch(Exception e)
		{
			System.out.println("AddTrialsRefree servlet "+e.getMessage());
		}
	}

}
