import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

public class ActivityEngine {
	
	
	int days;
	List<EventsObject> EventList;
	List<StatsObject> StatsList;
	
	Random in = new Random();
	
	
	public ActivityEngine(int days, List<EventsObject> eventList, List<StatsObject> statsList) {
		super();
		this.days = days;
		EventList = eventList;
		StatsList = statsList;
	}


	public void createLog()
	{
		
		for(int j=0; j<EventList.size(); j++)
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
	}
	
}
