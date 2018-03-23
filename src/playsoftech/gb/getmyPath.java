package playsoftech.gb;

import java.io.File;
import java.io.FilenameFilter;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.playsoftech.greenbox.dao.TrialsmemregDAO;

public class getmyPath {

	static final String prepath = "/home/pradeep/Documents/Mark-4/Greenbox2018/WebContent/";

	public static String gettour_imagePath() {
		return "" + prepath + "tour_images/";
	}

	public static String gettour_videoPath() {
		return "" + prepath + "tour_videos/";
	}

	public static String getImagesPath() {
		return "" + prepath + "images/";
	}

	public static String getTournamentPath() {
		return "" + prepath + "images/";
	}

	public static String getMemberPath() {
		return "" + prepath + "member/";
	}

	public static String getDefaultMemberimg()
	{
		return "images/default/noimage.svg";
	}
	public static String getDefaultLogo()
	{
		return "images/default/gblogo.png";
	}
	public static String getTrialMemberPath() {
		return "" + prepath + "images/trialmember/";
	}
	public static String getTrialRefreePath(){
		return ""+prepath+ "images/trialrefree/";
	}
	
	public static boolean isPhoto(String file_name) {
		if(file_name==null)
			return false;
		String filename = file_name.substring(file_name.lastIndexOf("/") + 1, file_name.length());
		String directory = prepath + file_name.substring(0, file_name.lastIndexOf("/") + 1);
		File f = new File(directory + filename);
		File d = new File(directory);

		if (d.isDirectory() && f.isFile()) {
			FilenameFilter filter = new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return (name.startsWith(filename.substring(0, filename.lastIndexOf(".")))
							&& name.endsWith(file_name.substring(file_name.lastIndexOf("."))));
				}
			};
			String flist[] = d.list(filter);
			if (flist != null) {
				for (int i = 0; i < flist.length; i++) {
					if (flist[i].equalsIgnoreCase(filename))
						return true;
				}
			}
		}
		return false;
	}

	public static String getPhoto(String image_path)
	{
		try
		{
			if(image_path != null && !image_path.isEmpty())
			{
				String photo=image_path.substring(image_path.lastIndexOf("/images") + 1,image_path.length());
				if(getmyPath.isPhoto(photo))
					return photo;
			}
			else{
				return getmyPath.getDefaultMemberimg();
			}
		}
		catch(Exception e) {
			System.out.println("getmyPath getPhoto() "+e);
			return getmyPath.getDefaultMemberimg();
		}
		return getmyPath.getDefaultMemberimg();
	}
	
	public static long getReg_id(long reg_id) {
		long newreg_id = 0;
		try {
			
			String regId = Long.toString(reg_id).substring(0,Long.toString(reg_id).length());
			newreg_id = Long.parseLong(regId);
		}
		catch(Exception e) {
			System.out.println("Exception getPath getReg_id:"+e.getMessage());
		}
		return newreg_id;
	}
	
	public static String NumSuffix(final Object number) {
	
		int n = Integer.parseInt(number.toString());
		if (n >= 11 && n <= 13) {
			return "th";
		}
		switch (n % 10) {
		case 1:
			return "st";
		case 2:
			return "nd";
		case 3:
			return "rd";
		default:
			return "th";
		}
	}

	public static String getSuffixDate(String date) {
		Date today;
		StringBuffer sb = new StringBuffer();
		try {
			today = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			Format formatter = new SimpleDateFormat("EEEE, dd MMMM yyyy");
			String m_date[] = formatter.format(today).split(" ");
		
			
			m_date[1] = m_date[1] + NumSuffix(m_date[1]);
			for (int i = 0; i < m_date.length; i++)
				sb.append(m_date[i]+" ");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sb.toString();

	}
	public static String getStringDate(String date) {
		String dt = null;
		try {
			Date today = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			Format formatter = new SimpleDateFormat("dd MMMM yyyy");
			dt=formatter.format(today);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt;

	}
	
	public static long getNewBarcodeId()
	{
	
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		String time = c.get(Calendar.YEAR)+""+(c.get(Calendar.MONTH)+1)+""+c.get(Calendar.DATE)+""+c.get(Calendar.HOUR)+""+c.get(Calendar.MINUTE)+""+c.get(Calendar.SECOND)+""+c.get(Calendar.MILLISECOND);
		String barcode_id [] = time.split("20");
		long barcode = Long.parseLong(barcode_id[1]);
		return barcode;
	}
	
	
	public static String getAge(Date birthDate)
	   {
	      int years = 0;
	      int months = 0;
	      int days = 0;
	      String age = "";
	      Calendar birthDay = Calendar.getInstance();
	      birthDay.setTimeInMillis(birthDate.getTime());
	      
	      long currentTime = System.currentTimeMillis();
	      Calendar now = Calendar.getInstance();
	      now.setTimeInMillis(currentTime);
	      years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
	      int currMonth = now.get(Calendar.MONTH) + 1;
	      int birthMonth = birthDay.get(Calendar.MONTH) + 1;
	      
	      months = currMonth - birthMonth;
	      if (months < 0)
	      {
	         years--;
	         months = 12 - birthMonth + currMonth;
	         if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
	            months--;
	      } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
	      {
	         years--;
	         months = 11;
	      }
	    
	      if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
	         days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
	      else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
	      {
	         int today = now.get(Calendar.DAY_OF_MONTH);
	         now.add(Calendar.MONTH, -1);
	         days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
	      } else
	      {
	         days = 0;
	         if (months == 12)
	         {
	            years++;
	            months = 0;
	         }
	      }
	      
	      age = years+"" ;// + months + " Months, " + days + " Days";
	      return age;
	   }
}
