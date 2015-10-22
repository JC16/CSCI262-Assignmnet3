import java.util.*;

public class AlterEngine {

	List<Double> sampleData;
	int weight;
	StatsObject stat;
	
	
	public AlterEngine(List<Double> sampleData, int weight, StatsObject stat) {	
		this.sampleData = sampleData;
		this.weight = weight;
		this.stat = stat;
	}

	public void calculateThreshold(){
		
		int count = 1;
		for(int i=0; i<sampleData.size();i++){
			double threshold = weight * sampleData.get(i);
			double calc = Math.abs(sampleData.get(i) - stat.getMean())/stat.getSD();
			if(calc * weight >= threshold){
				System.out.println("Day"+count+": NOT OK");
			}else
			{
				System.out.println("Day"+count+": OK");
			}
			
			count++;
			
		}
	}
	
	
}
