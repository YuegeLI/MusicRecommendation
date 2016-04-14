package Class;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class User {
	
	String userId;
	HashMap<Integer, TimePeriodSongs> songsTimePeriod; //index: timePeriod, vlaue: songs listened in this time period
	HashMap<Integer, Double> meansTimePeriod; //index: timePeriod, value: bias in this timePeriod
	TimePeriodSongs songsWithoutTP; //songs listened, used in without time period
	double meanWithoutTP; //mean value used in without time period
	
	public User(String uid){
		this.userId = uid;
		this.songsTimePeriod = new HashMap<Integer, TimePeriodSongs>();
		this.meansTimePeriod = new HashMap<Integer, Double>();
		this.songsWithoutTP = new TimePeriodSongs(0);
	}
	
	/**
	 * one more song information about one user in one time period comming, add this information in this user
	 * 
	 * @param songName
	 * @param timePeriod
	 * @param times
	 */
	public void add(String songName, int timePeriod, double times){
		TimePeriodSongs tps = null;
		if(songsTimePeriod.containsKey(timePeriod)){
			tps = songsTimePeriod.get(timePeriod);
		}else{
			tps = new TimePeriodSongs(timePeriod);
			songsTimePeriod.put(timePeriod,  tps);
		}
		tps.add(songName, times);
	}
	
	/**
	 * there are only one timesPeriodSongs, without TP, this timePeroid will be labeled 0
	 * 
	 * @param songName
	 * @param times
	 */
	public void addWithoutTP(String songName, double times){
		this.songsWithoutTP.add(songName, times);
	}
	
	/**
	 * cal the mean times of in this period time that each song listened times
	 */
	public void mean(){
		Set<Entry<Integer, TimePeriodSongs>> entrySet = songsTimePeriod.entrySet();
		for (Entry<Integer, TimePeriodSongs> entry : entrySet) {
			this.meansTimePeriod.put(entry.getKey(), entry.getValue().getMean());
		}
	}
	
	/**
	 * mean value used in without time period
	 */
	public void meanWithoutTP(){
		this.meanWithoutTP = this.songsWithoutTP.getMean();
	}
	/**
	 * used to print all userBias
	 * @return
	 */
	public HashMap<Integer, Double> getMeansTimePeriod() {
		return meansTimePeriod;
	}

	/**
	 * get mean value, used in without time period
	 * @return
	 */
	public double getMeanWithoutTP() {
		return meanWithoutTP;
	}

	public double getMean(int timePeriod){
		return this.meansTimePeriod.get(timePeriod);
	}

}
