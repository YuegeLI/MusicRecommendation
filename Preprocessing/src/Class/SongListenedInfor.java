package Class;

/**
 * one song listened times by how many different users in different time period
 * 
 * @author Yuege LI
 *
 */
public class SongListenedInfor {
	
	String songName;
	double times; //total number of this song listened by users
	double numTP; //the number of different users in different time period listened this song
	double mean; //the mean value of this song listened by the different people in different time period
	
	public SongListenedInfor(String sn){
		this.songName = sn;
		this.times = 0;
		this.numTP = 0;
		this.mean = 0;
	}
	
	/**
	 * one more song information about one user in one time period comming, add this information in this songListenedInfor
	 * 
	 * @param t: how many times that this song listened by this user in this time period, add this number to times
	 */
	public void add(double t){
		this.times += t;
		this.numTP += 1;
	}
	
	/**
	 * cal the mean value of this song
	 */
	public void calMean(){
		this.mean = times / numTP;
	}

	public String getSongName() {
		return songName;
	}

	public double getMean() {
		return mean;
	}
	
	
	

}
