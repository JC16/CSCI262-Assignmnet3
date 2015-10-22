import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AnalysisEngine {
	
	int days;
	List<EventsObject> EventList;
	List<StatsObject> StatsList;
	double mean;
	double SD;
	
	List<StatsObject> outputList = new ArrayList<StatsObject>();
	
	
	public AnalysisEngine(int days, List<EventsObject> eventList, List<StatsObject> statsList) {
		super();
		this.days = days;
		EventList = eventList;
		StatsList = statsList;
	}
	
	//Read the log file and calculate the mean and SD
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
					
					StatsObject newStats = new StatsObject();
					newStats.setEventName(EventList.get(j).Eventname);
					newStats.setMean(mean);
					newStats.setSD(SD);
					
					outputList.add(newStats);
					
					System.out.println(EventList.get(j).getEventname());
					
					AlterEngine ALE = new AlterEngine(sampleData,EventList.get(j).getWeight(),newStats);
					
					ALE.calculateThreshold();
					
					
				}
				
				else if(EventList.get(j).type.equalsIgnoreCase("C"))
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
					
					StatsObject newStats = new StatsObject();
					newStats.setEventName(EventList.get(j).Eventname);
					newStats.setMean(mean);
					newStats.setSD(SD);
					
					outputList.add(newStats);
					
					System.out.println(EventList.get(j).getEventname());
					
					AlterEngine ALE = new AlterEngine(sampleData,EventList.get(j).getWeight(),newStats);
					
					ALE.calculateThreshold();
					
					
				}
				
				else if(EventList.get(j).type.equalsIgnoreCase("E"))
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
					
					StatsObject newStats = new StatsObject();
					newStats.setEventName(EventList.get(j).Eventname);
					newStats.setMean(mean);
					newStats.setSD(SD);
					
					outputList.add(newStats);
					
					System.out.println(EventList.get(j).getEventname());
					
					AlterEngine ALE = new AlterEngine(sampleData,EventList.get(j).getWeight(),newStats);
					
					ALE.calculateThreshold();
					
					
				}
				
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	//read the line in the file check if it's number
	public boolean isNumeric(String str)
	{
		try  
		  {  
		    double d = Double.parseDouble(str);  
		  }  
		  catch(NumberFormatException nfe)  
		  {  
		    return false;  
		  }  
		  return true;
	}
	
	//Calculate the mean and standard deviation
	public void calculateSD(List<Double> list)
	{
		double mean = 0.0;
		
		for(int i=0; i<list.size(); i++)
		{
			mean += list.get(i);
		}
		
		mean = mean/(double)(list.size());
		
		double SD = 0.0;
		
		for(int i=0; i<list.size(); i++)
		{
			SD += (list.get(i)-mean)*(list.get(i)-mean);
		}
		
		SD = Math.sqrt(SD/(double)(list.size()-1));
		
		this.mean = mean;
		
		this.SD = SD;
		
	}
	
	public void printNewSD()
	{
		try
		{
			PrintWriter out = new PrintWriter("NewStats.txt");
			
			for(int i=0; i<outputList.size(); i++)
			{		
					out.println(outputList.get(i).eventName+":"+
							new DecimalFormat("##.##").format(outputList.get(i).mean)
							+":"+new DecimalFormat("##.##").format(outputList.get(i).SD));	
			}
					
			out.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
