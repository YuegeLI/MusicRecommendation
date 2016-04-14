package Distribution;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Distribution_Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filePath = "./data/TP/trainTP.txt";
		HashMap<Double, Double> dist = new HashMap<Double, Double>();
		
		try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ 
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                String itrs[] = null;
                double rate = 0;
                while((lineTxt = bufferedReader.readLine()) != null){
//                    System.out.println(lineTxt);
                	itrs = lineTxt.split("\\*\\:\\=");
                	rate = Math.round(Double.parseDouble(itrs[2]));
                	if(dist.containsKey(rate)){
                		double tmp = dist.get(rate);
                		dist.put(rate, tmp+1);
                	}else{
                		dist.put(rate, (double) 1);
                	}
                }
                read.close();
            }else{
            	System.out.println("cannot find file");
            }
		} catch (Exception e) {
			System.out.println("mistakes");
			e.printStackTrace();
		}
		
		Set<Entry<Double, Double>> entrySet = dist.entrySet();
		for (Entry<Double, Double> entry : entrySet) {
			System.out.println(entry.getKey()+"\t"+entry.getValue());
		}

	}

}
