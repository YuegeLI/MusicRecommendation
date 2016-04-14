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
import Class.UserListenedSong;

/**
 * count songs listened how many times 
 * in how many different time period by different users
 * 
 * @author Yuege LI
 *
 */
public class SongsBiasTP {
	
	HashMap<String, SongListenedInfor> songsListened = new HashMap<String, SongListenedInfor>(); //index: songsName, value: songs listened times infor
	
	/**
	 * 
	 * @param usersListenedSongs: how many times and in which time period that songs listened by users
	 */
	public SongsBiasTP(HashMap<String, HashMap> usersListenedSongs){
		
		count(usersListenedSongs);
		//cal mean value of each song
		Set<Entry<String, SongListenedInfor>> entrySet = songsListened.entrySet();
		for (Entry<String, SongListenedInfor> entry : entrySet) {
			entry.getValue().calMean();
		}
		write();
		
	}
	
	public void count(HashMap<String, HashMap> usersListenedSongs){
		Set<Entry<String, HashMap>> usersEntrySet = usersListenedSongs.entrySet(); //index: userId
		for (Entry<String, HashMap> usersEntry : usersEntrySet) {
			
			HashMap<String, UserListenedSong> userListenedSongs = usersEntry.getValue(); //index: song
			Set<Entry<String, UserListenedSong>> songEntrySet = userListenedSongs.entrySet();
			for (Entry<String, UserListenedSong> songEntry : songEntrySet) {
				
				String songName = songEntry.getKey();
				UserListenedSong uls = songEntry.getValue();
				HashMap<Integer, Double> count_timePeriod = uls.getCount_timePeriod(); //index: time period
				Set<Entry<Integer, Double>> tpEntrySet = count_timePeriod.entrySet();
				for (Entry<Integer, Double> tpEntry : tpEntrySet) {
					
					double times = tpEntry.getValue();
					SongListenedInfor song = null;
                    if(this.songsListened.containsKey(songName)){
                    	song = this.songsListened.get(songName);
                    }else{
                    	song = new SongListenedInfor(songName);
                    	this.songsListened.put(songName, song);
                    }
                    song.add(times);
				}
			}
		}
	}
	
	public HashMap<String, SongListenedInfor> getSongsListened() {
		return songsListened;
	}
	
	public void write(){
		File file = new File("./data/TP/songBiasTP.txt");
        FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            
            Set<Entry<String, SongListenedInfor>> entrySet = songsListened.entrySet();
            for (Entry<String, SongListenedInfor> entry : entrySet) {
            	String songName = entry.getKey();
            	double mean = entry.getValue().getMean();
			
	            String line = songName + "\t" + mean;
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

	public void toPrint(){
		Set<Entry<String, SongListenedInfor>> entrySet = songsListened.entrySet();
		for (Entry<String, SongListenedInfor> entry : entrySet) {
			System.out.println(entry.getKey() + "\t" + entry.getValue().getMean());
		}
	}

}
