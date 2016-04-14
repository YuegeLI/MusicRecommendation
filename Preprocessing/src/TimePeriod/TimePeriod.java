package TimePeriod;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author yogurt
 * split songs of one user into time period, based on userid, timestamp, writing into certain txt file
 */
public class TimePeriod {
	
	public TimePeriod(String userId, int time, String song){
		String timePeriod = arrangeTimePeriod(time);
		write(userId, timePeriod, song);
	}
	
	/**
	 * 
	 * @param time: timestamp of this user listen this song
	 * @return timeperiod of this song belongs to
	 */
	public String arrangeTimePeriod(int time){
		String timePeriod = "";
		if(time <= 5){
			timePeriod = "1";
		}
		if(time > 5 && time <= 11){
			timePeriod = "2";
		}
		if(time > 11 && time <= 17){
			timePeriod = "3";
		}
		if(time > 17){
			timePeriod = "4";
		}
		
		return timePeriod;
	}
	
	public void write(String userId, String timePeriod, String song){
		File file = new File("./data/timePeriod/" + userId + ".txt");
        FileWriter fw = null;
        BufferedWriter writer = null;
        
        try {
        	if(!file.exists()){
 	 	       file.createNewFile();
 	 	      }
        	
            fw = new FileWriter(file, true);
            writer = new BufferedWriter(fw);
                writer.write(userId + "\t" + timePeriod + "\t" + song);
                writer.newLine();//换行
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                writer.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}

}
