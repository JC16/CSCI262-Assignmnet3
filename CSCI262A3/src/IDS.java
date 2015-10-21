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
		
		//Check if the argument is input correctly
		if(args.length == 4)
		{
			eventFile = args[0];
			username = args[1];
			statFile = args[2];
			days = Integer.parseInt(args[3]);
		}
		else
		{
			System.out.println("Please input the correct number of argument");
			System.exit(0);
		}
		
		ReadFile(eventFile,statFile, days);
		
		
	}
	
	public static void ReadFile(String eventFile, String StatsFile, int days)
	{
		try {
			
			//Read the Event.txt
			FileInputStream EventIn = new FileInputStream(eventFile);
			BufferedReader buff = new BufferedReader(new InputStreamReader(EventIn));
			
			String strLine;
			
			//Create a Event object list to store every event
			List<EventsObject> EventList = new ArrayList<EventsObject>();
			
			
			//Read in information
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
			
			
			//Read in the Stats.txt
			FileInputStream StatsIn = new FileInputStream(StatsFile);
			BufferedReader buffStat = new BufferedReader(new InputStreamReader(StatsIn));
			
			//Create a Stats object list to store event information
			List<StatsObject> StatsList = new ArrayList<StatsObject>();
			
			//Read information
			while ((strLine = buffStat.readLine()) != null)   {
				  
				  if(strLine.indexOf(":")>=0)
				  {
					  String separator[] = strLine.split(":");
					  
					  StatsObject stats = new StatsObject();
					  
					  stats.setEventName(separator[0]);
					  stats.setMean(Double.parseDouble(separator[1]));
					  stats.setSD(Double.parseDouble(separator[2]));
					  
					  StatsList.add(stats);
					  
				  }
				
			}
			
			buff.close();
			buffStat.close();
			
			
			ActivityEngine AE = new ActivityEngine(days, EventList, StatsList);
			
			AE.createLog();
			
			AnalysisEngine AnE = new AnalysisEngine(days, EventList, StatsList);
			
			AnE.readAnalysis();
			
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
