package TargetWithoutTP;

import java.io.File;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import Class.SongListenedInfor;
import Class.User;
import Class.UserListenedSong;
import TargetTP.SongsBiasTP;
import TargetTP.TargetTP;
import TargetTP.UserBiasTP;
import TargetTP.UserListenedTimesCount;

public class TargetWithoutTP_Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HashMap<String, HashMap> usersListenedSongs = new HashMap<String, HashMap>(); //index: userId, value: HashMap about the songs listened by this user
		
		File dirPath = new  File("./data/timePeriod");
		for (File child : dirPath.listFiles()) {
			if (".".equals(child.getName()) || "..".equals(child.getName()) || child.getName().charAt(0) == '.') {
				continue; // Ignore the self and parent aliases.
			}
			
			System.out.println(child);
			String filePath = child.toString();
			String userId = filePath.split("/")[3].split("\\.")[0];
			
			UserListenedCount lr = new UserListenedCount(filePath, userId);
			HashMap<String, UserListenedSong> listenedSongs = lr.getListenedSongs();
			usersListenedSongs.put(userId, listenedSongs);
		}
		
		//cal users mean /user bias
		System.out.println("Users Mean ...");
		UserBiasWithoutTP us = new UserBiasWithoutTP(usersListenedSongs);
		HashMap<String, User> users_mean = us.getUsers();
		System.out.println("Users Mean Done.");
		
		//replace with times-usersBias
		System.out.println("Replace Users Mean ...");
		Set<Entry<String, HashMap>> usersEntrySet = usersListenedSongs.entrySet(); //index: userId
		for (Entry<String, HashMap> usersEntry : usersEntrySet) {
			
			String userId = usersEntry.getKey();
			HashMap<String, UserListenedSong> songs = usersEntry.getValue(); //index: song name
			Set<Entry<String, UserListenedSong>> songEntrySet = songs.entrySet();
			for (Entry<String, UserListenedSong> songEntry : songEntrySet) {
				
				songEntry.getValue().replaceUserBiasWithoutTP(users_mean.get(userId).getMeanWithoutTP());
			}
			UserListenedCount lr = new UserListenedCount(userId, songs); //to print the result after replacing
		}
		System.out.println("Replace Users Mean Done.");
		
		//cal songs mean / song bias
		System.out.println("Songs Mean ...");
		SongsBiasWithoutTP sc = new SongsBiasWithoutTP(usersListenedSongs);
//		sc.toPrint();
		HashMap<String, SongListenedInfor> songsListened_mean = sc.getSongsListened();
		System.out.println("Songs Mean Done.");
		
		//cal target
		System.out.println("Target ...");
		TargetWithoutTP t = new TargetWithoutTP(usersListenedSongs, songsListened_mean, users_mean);
		System.out.println("Target Done.");
		
		
		System.out.println("Done");

	}

}
