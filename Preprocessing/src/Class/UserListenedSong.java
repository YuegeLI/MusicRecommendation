package Class;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * @author Yuege LI
 * infor about one song that listened information by one user
 * listened timePeriod, times
 * 
 */
public class UserListenedSong {
	
	String songName;
	double count; //total number of listening this song
	HashMap<Integer, Double> target_timePeriod; //number of listening this song in this time period, index: time period, value: listened times
//	HashMap<Integer, Double> target_timePeriod; //target to this song in this timePeriod
	double target;
	
	public UserListenedSong(String sn){
		this.songName = sn;
		
		this.count = 0;
		this.target_timePeriod = new HashMap<Integer, Double>();
		this.target = 0;
	}
	
	/**
	 * add one more time that user listened this song in this time period
	 * 
	 * @param tp: timePeriod label
	 */
	public void add(int tp){
		if(target_timePeriod.containsKey(tp)){
			double tmp = target_timePeriod.get(tp);
			target_timePeriod.put(tp, tmp+1);
		}else{
			target_timePeriod.put(tp, (double) 1);
		}
		
		this.target += 1; //total number of listened this song
	}
	
	/**
	 * replace the pure times of listening this song by times-mean
	 * used in timePeriod
	 * 
	 * @param meansTimePeriod: mean value of this user listened songs in one time period, index: time period, value: mean
	 */
	public void replaceUserBiasTP(HashMap<Integer, Double> meansTimePeriod){
		Set<Entry<Integer, Double>> entrySet = this.target_timePeriod.entrySet();
		for (Entry<Integer, Double> entry : entrySet) {
			
			int timePeriod = entry.getKey();
			this.target_timePeriod.put(timePeriod, entry.getValue()-meansTimePeriod.get(timePeriod));
		}
	}
	
	public void replaceUserBiasWithoutTP(double mean){
		this.target -= mean;
	}
	
	public void rate(double totalSongs){
		this.target = -Math.log(count/totalSongs);
	}
	
	public double getTarget() {
		return target;
	}

	public String getSongName() {
		return songName;
	}

	public HashMap<Integer, Double> getCount_timePeriod() {
		return target_timePeriod;
	}

	public double getCount() {
		return count;
	}
}
