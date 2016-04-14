package Class;

import java.util.HashMap;

/**
 * in one timePeriod what songs listened by this user, and how many times listened this song
 * 
 * @author Yuege LI
 *
 */
public class TimePeriodSongs {
	
	int timePeriod;
	HashMap<String, Double> songsCount; //index: song, value: listened how many times
	double totalNumber; //total number of songs times listened in this time period
	double mean; //user bias in this time period
	
	public TimePeriodSongs(int tp){
		this.timePeriod = tp;
		this.songsCount = new HashMap<String, Double>();
		this.totalNumber = 0;
		this.mean = 0;
	}
	
	public void add(String songName, double times){
		songsCount.put(songName, times);
		this.totalNumber += times;
	}

	public double getMean() {
		this.mean = totalNumber / songsCount.size();
		return mean;
	}
	
	

}
