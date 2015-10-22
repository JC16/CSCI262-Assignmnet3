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
							int loginNum = in.nextInt((int)(StatsList.get(j).mean + 3*StatsList.get(j).SD));	//mean + 2*standard deviation + 1
							if(loginNum >= (int)(StatsList.get(j).mean - 3*StatsList.get(j).SD)){				//mean - 2*standard deviation
								out.println("Day" + dayCount);
								out.println(loginNum);	//total recorded number
								for(int i=0;i<loginNum; i++ ){
									out.println(EventList.get(j).Eventname);
								}
								out.println();
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
						int onlineNum = in.nextInt((int)(Integer.parseInt(EventList.get(j).getMaximum())/StatsList.get(j).mean))+1;	//max value divide mean to obtain average number
						{			
							out.println("Day" + dayCount);	//write day info
							out.println("Total Events per day:"+onlineNum);		//total recorded number
							for(int i=0;i<onlineNum;){
								double onlineData = in.nextFloat() * (int)(StatsList.get(j).mean + 3*StatsList.get(j).SD);	//generate random number less than mean + 2*standard deviation
								if (onlineData >= StatsList.get(j).mean - 3*StatsList.get(j).SD){
									double value = Double.valueOf(String.format("%.2f", onlineData));	//keep two digital number
									totalPerDay += value;	//update total value per day
									
									out.println(EventList.get(j).Eventname + value);
									//out.println(value);	//write value to log file
									i++;
								}
							}
							out.println();
							out.println(String.format("%.2f", totalPerDay/onlineNum));	//write total value per day
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
			else if (EventList.get(j).type.equalsIgnoreCase("E"))
			{
				try{
					PrintWriter out = new PrintWriter(EventList.get(j).Eventname+".log");
					boolean done = false;
					int dayCount = 1;
					while(!done){
						long totalPerDay = 0;	//total value per day
						int num = in.nextInt(100)+1;	//define max 100 records per day
						{	
							out.println("Day" + dayCount);	//write day info
							out.println("Total Events per day:"+num);		//total recorded number
							for(int i=0;i<num;){
								int data = in.nextInt((int)(StatsList.get(j).mean + 3*StatsList.get(j).SD));	//generate random number less than mean + 2*standard deviation
								if(EventList.get(j).getMinimum().equals("0")){	//download event list	
									//if (data >= StatsList.get(j).mean - 2*StatsList.get(j).SD){
										totalPerDay += data;	//update total value per day
										out.println(EventList.get(j).Eventname + data);
										//out.println(value);	//write value to log file
										i++;
									//}
								}
								else	//money made event
								{
									if (data <= Math.abs(StatsList.get(j).mean - 3*StatsList.get(j).SD)){
										int judgeSymbol = in.nextInt(3);
										if(judgeSymbol == 0)
											data = -data;
									}
										totalPerDay += data;	//update total value per day
										out.println(EventList.get(j).Eventname + data);
										//out.println(value);	//write value to log file
										i++;
									
								}
							}
							out.println();
							out.println(totalPerDay/num);	//write total value per day
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
