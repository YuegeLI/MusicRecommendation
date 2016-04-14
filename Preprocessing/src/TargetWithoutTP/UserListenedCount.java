package TargetWithoutTP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import Class.UserListenedSong;

/**
 * 
 * @author Yuege LI
 * 
 * Count how many time that this user listened this songs in this time period
 *
 */
public class UserListenedCount {
	
	HashMap<String, UserListenedSong> listenedSongs; //record infor about listening songs, index: songs' name, value: class song
	
	public UserListenedCount(String fp, String userId){
		
		this.listenedSongs = new HashMap<String, UserListenedSong>();
		load(fp);
		write(userId, "./data/withoutTP/userCountWithoutTP_userId_songName_timesListened.txt");
		
	}
	
	public UserListenedCount(String userId, HashMap<String, UserListenedSong> ls){
		this.listenedSongs = ls;
		write(userId, "./data/withoutTP/afterReplacedWithoutTP_userID_songName_timesListened.txt");
	}
	
	/**
	 * read file from ./data/timePeriod
	 * 
	 * @param filePath: file path in package ./data/timePeriod
	 */
	public void load (String filePath) {
		
		try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ 
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                
                while((lineTxt = bufferedReader.readLine()) != null){
                    String[] itrs = lineTxt.split("\t");
                    int timePeriod = Integer.parseInt(itrs[1]);
                    String songName = itrs[2];
                    UserListenedSong song = null;
                    if(listenedSongs.containsKey(songName)){
                    	song = listenedSongs.get(songName);
                    }else{
                    	song = new UserListenedSong(songName);
                    	listenedSongs.put(songName, song);
                    }
                    song.add(timePeriod);
                }
                read.close();
            }else{
            	System.out.println("cannot find file");
            }
		} catch (Exception e) {
			System.out.println("mistakes");
			e.printStackTrace();
		}
	}
	
	public void write(String userId, String filePath){
		File fileCount = new File(filePath);
        FileWriter fwCount = null;
        BufferedWriter writerCount = null;
        
        try {
            fwCount = new FileWriter(fileCount, true);
            writerCount = new BufferedWriter(fwCount);
            
            Set<Entry<String, UserListenedSong>> songsEntrySet = listenedSongs.entrySet();
    		for (Entry<String, UserListenedSong> songEntry : songsEntrySet) {
    			UserListenedSong s = songEntry.getValue();
    			String songName = s.getSongName();
    			
    			//userId songName timesListened
    			String line = userId + "\t" + songName + "\t" + s.getTarget();
    			writerCount.write(line);
                writerCount.newLine();
    		}
            writerCount.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                writerCount.close();
                fwCount.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

	public HashMap<String, UserListenedSong> getListenedSongs() {
		return listenedSongs;
	}
	
	

}
