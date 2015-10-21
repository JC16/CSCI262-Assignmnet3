import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AnalysisEngine {
	
	int days;
	List<EventsObject> EventList;
	List<StatsObject> StatsList;
	
	
	public AnalysisEngine(int days, List<EventsObject> eventList, List<StatsObject> statsList) {
		super();
		this.days = days;
		EventList = eventList;
		StatsList = statsList;
	}
	
	public void readAnalysis()
	{
		try {
			for(int j=0; j<EventList.size(); j++)
			{
				//Check the event type
				
				List<Double> sampleData = new ArrayList<Double>();
				
				if(EventList.get(j).type.equalsIgnoreCase("D"))
				{
					FileInputStream EventIn;
					
						EventIn = new FileInputStream(EventList.get(j).Eventname+".log");
					
					BufferedReader buff = new BufferedReader(new InputStreamReader(EventIn));
					
					String strLine;
					
					while ((strLine = buff.readLine()) != null)   {
						
						if(isNumeric(strLine))
						{
							sampleData.add(Double.parseDouble(strLine));
						}
						
					}
					
					calculateSD(sampleData);
					
				}
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public boolean isNumeric(String str)
	{
		try  
		  {  
		    int d = Integer.parseInt(str);  
		  }  
		  catch(NumberFormatException nfe)  
		  {  
		    return false;  
		  }  
		  return true;
	}
	
	public void calculateSD(List<Double> list)
	{
		
		double mean = 0.0;
		
		for(int i=0; i<list.size(); i++)
		{
			mean += list.get(i);
		}
		
		System.out.println(mean);
		
		mean = mean/(double)(list.size());
		
		
		double SD = 0.0;
		
		for(int i=0; i<list.size(); i++)
		{
			SD += (list.get(i)-mean)*(list.get(i)-mean);
		}
		
		SD = Math.sqrt(SD/(double)(list.size()-1));
		
		System.out.println(new DecimalFormat("##.##").format(mean));
		System.out.println(new DecimalFormat("##.##").format(SD));
		
		
	}
	
}
