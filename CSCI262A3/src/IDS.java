import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class IDS {
	
	
	public static void main(String args[])
	{
	
		String eventFile = null;
		String statFile = null;
		String username = null;
		int days = 0;
		
		
		/*eventFile = args[0];
		statFile = args[1];
		username = args[2];
		days = Integer.parseInt(args[3]);*/
		
		if(args.length == 4)
		{
			eventFile = args[0];
			statFile = args[1];
			username = args[2];
			days = Integer.parseInt(args[3]);
		}
		else
		{
			System.out.println("Please input the correct number of argument");
			System.exit(0);
		}
		
		System.out.println("Event "+eventFile);
		System.out.println("Stat "+statFile);
		System.out.println("User "+username);
		System.out.println("Days "+days);
		
		try {
			
			FileInputStream EventIn = new FileInputStream(eventFile);
			BufferedReader buff = new BufferedReader(new InputStreamReader(EventIn));
			
			String strLine;
			
			
			
			List<EventsObject> EventList = new ArrayList<EventsObject>();
			
			
			while ((strLine = buff.readLine()) != null)   {
				  
				  if(strLine.indexOf(":")>=0)
				  {
					  String separator[] = strLine.split(":");
					  
					  EventsObject Eobject = new EventsObject();
					  
					  Eobject.setEventname(separator[0]);
					  Eobject.setType(separator[1]);
					  Eobject.setMinimum(separator[2]);
					  Eobject.setMaximum(separator[3]);
					  Eobject.setUnits(separator[4]);
					  Eobject.setWeight(Integer.parseInt(separator[5]));
					  
					  EventList.add(Eobject);
					  
				  }
				
			}
			
			buff.close();
			
			
			for(int i=0; i<EventList.size(); i++)
			{
				System.out.println(EventList.get(i).getEventname());
				System.out.println(EventList.get(i).getType());
				System.out.println(EventList.get(i).getMinimum());
				System.out.println(EventList.get(i).getMaximum());
				System.out.println(EventList.get(i).getUnits());
				System.out.println(EventList.get(i).getWeight());
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
