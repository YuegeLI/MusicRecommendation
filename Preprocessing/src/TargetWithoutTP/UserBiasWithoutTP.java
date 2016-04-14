package TargetWithoutTP;

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

public class UserBiasWithoutTP {
	
	HashMap<String, User> users; //index: userId, value: songs listened by this user in which time period and how many times
	
	public UserBiasWithoutTP(HashMap<String, HashMap> usersListenedSongs){
		
		users = new HashMap<String, User>();
		
		//count in one time period, one user listened what songs and how many times
		count(usersListenedSongs);
		
		//cal mean value of this user
		Set<Entry<String, User>> entrySet = users.entrySet();
		for (Entry<String, User> entry : entrySet) {
			entry.getValue().meanWithoutTP();
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
				double times = uls.getTarget();
				User user = null;
                if(this.users.containsKey(userId)){
                	user = this.users.get(userId);
                }else{
                	user = new User(userId);
                	this.users.put(userId, user);
                }
                user.addWithoutTP(songName, times);
			}
		}
	}
	
	public void write(){
		File file = new File("./data/withoutTP/userBiasWithoutTP.txt");
        FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            
            Set<Entry<String, User>> userEntrySet = users.entrySet(); //index: user
            for (Entry<String, User> userEntry : userEntrySet) {
            	String userId = userEntry.getKey();
            	double mean = userEntry.getValue().getMeanWithoutTP();
            	
            	//userId timePeriod bias
            	String line = userId + "\t" + mean;
				writer.write(line);
                writer.newLine();//换行
	            writer.flush();
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
