package playsoftech.gb;

import java.util.Iterator;
import java.util.List;

import com.playsoftech.greenbox.dao.GetDAOList;
import com.playsoftech.greenbox.pojo.Events;

public class PhysicalMarksCal {
	public double getPhysicalMarksCal(long event_id,String time1) {
		//int marks=0;
		double marks = 0.0;
		try{
			
			GetDAOList getdaolist=new GetDAOList();
			List<?> eventlist=getdaolist.getTrialEvent(event_id);
			Iterator<?> eventsitr=eventlist.iterator();
			if(eventsitr.hasNext())
			{
				Events events = (Events)eventsitr.next();
				if(events.getEvent_name().equalsIgnoreCase("100 MTRS"))
				{
					double time=Double.parseDouble(time1);
					
					/*if(time <= 9.10)
					{
						marks=10;
					}
					else if(time >= 9.11 && time <= 9.40)
						marks=9;
					else if(time >= 9.41 && time <= 9.80)
						marks=8;
					else if(time >= 9.81 && time <= 10.20)
						marks=7;
					else if(time >= 10.21 && time <= 10.60)
						marks=6;
					else if(time >= 10.61 && time <= 11.00)
						marks=5;
					else if(time >= 11.01 && time <= 11.40)
						marks=4;
					else if(time >= 11.41 && time <= 11.80)
						marks=3;
					else if(time >= 11.81 && time <= 12.20)
						marks=2;
					else if(time >= 12.21)
						marks=1;
					else 
						marks=0;*/
					
					if(time>=9.0 && time<=9.50)
						marks = 10.0;		
					else if(time>=9.51 && time<=10.0)
						marks = 9.5;
					else if(time>=10.01 && time<=10.50)
						marks = 9.0;
					else if(time>=10.51 && time<=11.0)
						marks = 8.5;
					else if(time>=11.01 && time<=11.50)
						marks = 8.0;
					else if(time>=11.51 && time<=12.0)
						marks = 7.5;
					else if(time>=12.01 && time<=12.50)
						marks = 7.0;
					else if(time>=12.51 && time<=13.0)
						marks = 6.5;
					else if(time>=13.01 && time<=13.50)
						marks = 6.0;
					else if(time>=13.51 && time<=14.0)
						marks = 5.5;
					else if(time>=14.01 && time<=14.50)
						marks = 5.0;
					else if(time>=14.51 && time<=15.0)
						marks = 4.5;
					else if(time>=15.01 && time<=15.50)
						marks = 4.0;
					else if(time>=15.51 && time<=16.0)
						marks = 3.5;
					else if(time>=16.01 && time<=16.50)
						marks = 3.0;
					else if(time>=16.51 && time<=17.0)
						marks = 2.5;
					else if(time>=17.01 && time<=17.50)
						marks = 2.0;
					else if(time>=17.51 && time<=18.0)
						marks = 1.5;
					else if(time >=18.01)
						marks = 1.0;
				}
				else if(events.getEvent_name().equalsIgnoreCase("PUSH UPS"))
				{
					
					int time = Integer.parseInt(time1);
					
					if(time >= 91)
						marks=10;
					else if(time >= 81 && time <= 90)
						marks=9;
					else if(time >= 71 && time <= 80)
						marks=8;
					else if(time >= 61 && time <= 70)
						marks=7;
					else if(time >= 51 && time <= 60)
						marks=6;
					else if(time >= 41 && time <= 50)
						marks=5;
					else if(time >= 31 && time <= 40)
						marks=4;
					else if(time >= 21 && time <= 30)
						marks=3;
					else if(time >= 11 && time <= 20)
						marks=2;
					else if(time >= 0 && time <= 10)
						marks=1;
					else 
						marks=0;
					
				
				}
				else if(events.getEvent_name().equalsIgnoreCase("12 MINUTES R/W"))
				{
					double time = Double.parseDouble(time1);
					/*if(time >= 37)
						marks=10;
					else if(time >= 35 && time <= 36)
						marks=9;
					else if(time >= 33 && time <= 34)
						marks=8;
					else if(time >= 31 && time <= 32)
						marks=7;
					else if(time >= 29 && time <= 30)
						marks=6;
					else if(time >= 27 && time <= 28)
						marks=5;
					else if(time >= 25 && time <= 26)
						marks=4;
					else if(time >= 23 && time <=24)
						marks=3;
					else if(time >= 21 && time <= 22)
						marks=2;
					else if(time <= 20)
						marks=1;
					else 
						marks=0;*/
					
					if(time >= 18)
						marks=10;
					else if(time >= 17 && time <= 17)
						marks=9;
					else if(time >= 16 && time <= 16)
						marks=8;
					else if(time >= 15 && time <= 15)
						marks=7;
					else if(time >= 14 && time <= 14)
						marks=6;
					else if(time >= 13 && time <= 13)
						marks=5;
					else if(time >= 12 && time <= 12)
						marks=4;
					else if(time >= 11 && time <=11)
						marks=3;
					else if(time >= 10 && time <= 10)
						marks=2;
					else if(time <= 9)
						marks=1;
					else 
						marks=0;
					
				}
				else if(events.getEvent_name().equalsIgnoreCase("DRIBBLING"))
				{

					double time=Double.parseDouble(time1);
					
					if(time <=4.19)
						marks=10;
					else if(time >= 4.20 && time <= 5.19)
						marks=9;
					else if(time >= 5.20 && time <= 6.19)
						marks=8;
					else if(time >= 6.20 && time <= 7.19)
						marks=7;
					else if(time >= 7.20 && time <= 8.19)
						marks=6;
					else if(time >= 8.20 && time <= 9.19)
						marks=5;
					else if(time >= 9.20 && time <= 10.19)
						marks=4;
					else if(time >= 10.20 && time <=11.19)
						marks=3;
					else if(time >= 11.20 && time <= 12.19)
						marks=2;
					else if(time >=12.20)
						marks=1;
					else 
						marks=0;
				}
				else if(events.getEvent_name().equalsIgnoreCase("PLANK"))
				{
					int time = Integer.parseInt(time1);
					
					if(time >= 150)
					{
						marks = 10;
					}
					else if(time >=120 && time <=149)
					{
						marks = 8;
					}
					else if(time >= 75 && time < 120)
					{
						marks = 6;
					}
					else if(time >= 45 && time < 75)
					{
						marks = 3;
					}
					else if(time  >= 15 && time < 45)
					{
						marks = 2;
					}
					else if(time < 15)
					{
						marks = 1;
					}
					
				}
				
				else if(events.getEvent_name().equalsIgnoreCase("SHUTTLE RUN"))
				{
					int time = Integer.parseInt(time1);
					if(time >= 3)
					{
						marks = 10;
					}
					else if(time == 2)
					{
						marks = 6;
					}
					else if(time == 1)
					{
						marks = 3;
					}
					else
					{
						marks = 0;
					}
				}
				else if(events.getEvent_name().equalsIgnoreCase("REFLEX"))
				{
					int time = Integer.parseInt(time1);
					if(time >= 3)
					{
						marks = 10;
					}
					else if(time == 2)
					{
						marks = 6;
					}
					else if(time == 1)
					{
						marks = 3;
					}
					else
					{
						marks = 0;
					}
				}
			}
		}catch(Exception e)
		{
			System.out.println("Exception in playesoftech.gb PhysicalmarksCal"+e);
		}
		return marks;
	}
}
