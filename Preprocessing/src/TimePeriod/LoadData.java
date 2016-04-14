package TimePeriod;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author yogurt
 * read data, userid_timestamp_song
 */
public class LoadData {
	
	public LoadData(String fp){
		load(fp);
	}
	
	public void load(String filePath){
		try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ 
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                TimePeriod tp = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    String[] itrs = lineTxt.split("\t");
                    String userId = itrs[0];
                    System.out.println(userId);
                    int time = Integer.parseInt(itrs[1]);
                    String song = itrs[2];
                    tp = new TimePeriod(userId, time, song);
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

}
