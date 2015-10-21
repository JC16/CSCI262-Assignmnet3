import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

public class ActivityEngine {
	
	
	int days;
	List<EventsObject> EventList;
	List<StatsObject> StatsList;
	
	Random in = new Random();
	
	
	//Constructor put the number of days and the list object to the class
	public ActivityEngine(int days, List<EventsObject> eventList, List<StatsObject> statsList) {
		super();
		this.days = days;
		EventList = eventList;
		StatsList = statsList;
	}

	//Create log file function
	public void createLog()
	{
		//Create log file for all events
		for(int j=0; j<EventList.size(); j++)
		{
			//Check the event type
			if(EventList.get(j).type.equalsIgnoreCase("D"))
			{
				try{
						PrintWriter out = new PrintWriter(EventList.get(j).Eventname+".log");
						boolean done = false;
						int dayCount = 1;
						while(!done){
							int loginNum = in.nextInt((int)(StatsList.get(j).mean + 2*StatsList.get(j).SD));	//mean + 2*standard deviation + 1
							if(loginNum >= (int)(StatsList.get(j).mean - 2*StatsList.get(j).SD)){				//mean - 2*standard deviation
								out.println("Day" + dayCount);
								for(int i=0;i<loginNum; i++ ){
									out.println(EventList.get(j).Eventname);
								}
								out.println();
								out.println(loginNum);	//total recorded number
								out.println();
								dayCount++;
							}
							if(dayCount-1 == days){
								done = true;
								out.close();
							}
						}
					}catch(IOException e){
					e.printStackTrace();
					}
			}
			else if (EventList.get(j).type.equalsIgnoreCase("C"))
			{
				try{
					PrintWriter out = new PrintWriter(EventList.get(j).Eventname+".log");
					boolean done = false;
					int dayCount = 1;
					while(!done){
						double totalPerDay = 0.0;	//total value per day
						int onlineNum = in.nextInt((int)(StatsList.get(j).mean + 2*StatsList.get(j).SD));	//mean + 2*standard deviation + 1
						if(onlineNum >= (int)(StatsList.get(j).mean - 2*StatsList.get(j).SD)){				//mean - 2*standard deviation
							out.println("Day" + dayCount);	//write day info
							for(int i=0;i<onlineNum; i++ ){
								double onlineData = in.nextFloat() * (int)(StatsList.get(j).mean + 2*StatsList.get(j).SD);	//generate random number less than mean + 2*standard deviation
								double value = Double.valueOf(String.format("%.2f", onlineData));	//keep two digital number
								totalPerDay += value;	//update total value per day
								out.println(value);	//write value to log file
							}
							out.println();
							out.println(onlineNum);		//write recorded event number
							out.println(String.format("%.2f", totalPerDay));	//write total value per day
							out.println();
							dayCount++;
						}
						if(dayCount-1 == days){
							done = true;
							out.close();
						}
					}
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	
}
