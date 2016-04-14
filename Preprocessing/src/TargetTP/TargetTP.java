package TargetTP;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import Class.SongListenedInfor;
import Class.User;
import Class.UserListenedSong;

public class TargetTP {
	
	public TargetTP(HashMap<String, HashMap> usersListenedSongs, HashMap<String, SongListenedInfor> songs_mean, HashMap<String, User> users_mean){
		cal(usersListenedSongs, songs_mean, users_mean);
	}
	
	public void cal(HashMap<String, HashMap> usersListenedSongs, HashMap<String, SongListenedInfor> songs_mean, HashMap<String, User> users_mean){
		
		File fileTrain = new File("./data/TP/trainTP.txt");
		FileWriter fwTrain = null;
		BufferedWriter writerTrain = null;
		File fileTest = new File("./data/TP/testTP.txt");
		FileWriter fwTest = null;
		BufferedWriter writerTest = null;

		try {
			fwTrain = new FileWriter(fileTrain, true);
			writerTrain = new BufferedWriter(fwTrain);
			fwTest = new FileWriter(fileTest, true);
			writerTest = new BufferedWriter(fwTest);
		
			Set<Entry<String, HashMap>> usersEntrySet = usersListenedSongs.entrySet(); //index: userId
			for (Entry<String, HashMap> usersEntry : usersEntrySet) {
			
				String userId = usersEntry.getKey();
				System.out.println(userId);
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
						double target = times - songs_mean.get(songName).getMean();
						String line = userId + "*:=" + songName + "*:=" + target + "*:=" + timePeriod;
						
                    
						int rand = (int) (1+Math.random()*(10-1+1));
						if(rand <=2){
							writerTest.write(line);
							writerTest.newLine();
						}else{
							writerTrain.write(line);
							writerTrain.newLine();
						}
    		
						writerTrain.flush();
						writerTest.flush();
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				writerTrain.close();
				fwTrain.close();
				writerTest.close();
				fwTest.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
