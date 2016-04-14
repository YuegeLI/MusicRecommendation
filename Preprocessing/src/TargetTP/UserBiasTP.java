package TargetTP;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import Class.SongListenedInfor;
import Class.User;
import Class.UserListenedSong;

public class UserBiasTP {
	
	HashMap<String, User> users; //index: userId, value: songs listened by this user in which time period and how many times
	
	public UserBiasTP(HashMap<String, HashMap> usersListenedSongs){
		
		users = new HashMap<String, User>();
		
		//count in one time period, one user listened what songs and how many times
		count(usersListenedSongs);
		
		//cal mean value of this user
		Set<Entry<String, User>> entrySet = users.entrySet();
		for (Entry<String, User> entry : entrySet) {
			entry.getValue().mean();
		}
		write();
	}
	
	/**
	 * 
	 * @param usersListenedSongs: index: userId, value: HashMap about the songs listened by this user
	 */
	public void count(HashMap<String, HashMap> usersListenedSongs){
		Set<Entry<String, HashMap>> usersEntrySet = usersListenedSongs.entrySet(); //index: userId
		for (Entry<String, HashMap> usersEntry : usersEntrySet) {
			
			String userId = usersEntry.getKey();
			HashMap<String, UserListenedSong> userListenedSongs = usersEntry.getValue(); //index: song
			Set<Entry<String, UserListenedSong>> songEntrySet = userListenedSongs.entrySet();
			for (Entry<String, UserListenedSong> songEntry : songEntrySet) {
				
				String songName = songEntry.getKey();
				UserListenedSong uls = songEntry.getValue();
				HashMap<Integer, Double> count_timePeriod = uls.getCount_timePeriod(); //index: time period
				Set<Entry<Integer, Double>> tpEntrySet = count_timePeriod.entrySet();
				for (Entry<Integer, Double> tpEntry : tpEntrySet) {
					
					int timePeriod = tpEntry.getKey();
					double times = tpEntry.getValue();
					User user = null;
                    if(this.users.containsKey(userId)){
                    	user = this.users.get(userId);
                    }else{
                    	user = new User(userId);
                    	this.users.put(userId, user);
                    }
                    user.add(songName, timePeriod, times);
				}
			}
		}
	}

	public void write(){
		File file = new File("./data/TP/userBiasTP.txt");
        FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            
            Set<Entry<String, User>> userEntrySet = users.entrySet(); //index: user
            for (Entry<String, User> userEntry : userEntrySet) {
            	String userId = userEntry.getKey();
            	HashMap<Integer, Double> meansTimePeriod = userEntry.getValue().getMeansTimePeriod(); //index: timeperiod
			
            	Set<Entry<Integer, Double>> tpEntrySet = meansTimePeriod.entrySet();
            	for (Entry<Integer, Double> tpEntry : tpEntrySet) {
            		int timePeriod = tpEntry.getKey();
            		double mean = tpEntry.getValue();
				
				
				
		            //userId timePeriod bias
					String line = userId + "\t" + timePeriod + "\t" + mean;
					writer.write(line);
	                writer.newLine();//换行
		            writer.flush();
		        }
		    }
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
	
	public HashMap<String, User> getUsers() {
		return users;
	}

}
