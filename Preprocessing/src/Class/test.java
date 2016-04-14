package Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class test {
	
	public static void main( String[] args ){
//	    System.out.println((int)(1+Math.random()*(10-1+1)));
		
//	    HashMap<Integer, Double> v1 = new HashMap<Integer, Double>();
//	    HashMap<Integer, Double> v2 = new HashMap<Integer, Double>();
//	    
//	    v1.put(1, 2.0);
//	    v1.put(2, 4.0);
//	    v2.put(2, 5.0);
//	    v2.put(3, 3.0);
//	    
//	    System.out.println(pearson(v1, v2));
		
		double d = -6.538985626857288;
		System.out.println(Math.round(d));
	    
	    
	}
	
	public static double pearson(HashMap<Integer, Double> v1, HashMap<Integer, Double> v2) {

        Set<Integer> all = new HashSet(v1.keySet());
        all.addAll(v2.keySet());
        
        double sumV1 = 0, sumV2 = 0, averageV1 = 0, averageV2 = 0, sclar = 0, norm1 = 0, norm2 = 0;
        HashMap<Integer, Double> devV1 = new HashMap<Integer, Double>();
        HashMap<Integer, Double> devV2 = new HashMap<Integer, Double>();
        
        for(int k : v1.keySet())
        	sumV1 += v1.get(k);
        averageV1 = sumV1 / all.size();
        for(int k : v2.keySet())
        	sumV2 += v2.get(k);
        averageV2 = sumV2 / all.size();
        
        for(int k : all){
        	if(v1.keySet().contains(k))
        		devV1.put(k, (v1.get(k) - averageV1));
        	else
        		devV1.put(k, (0 - averageV1));
        	if(v2.keySet().contains(k))
        		devV2.put(k, (v2.get(k) - averageV2));
        	else
        		devV2.put(k, (0 - averageV2));
        }
        for (int k : all) sclar += devV1.get(k) * devV2.get(k);
        for (int k : devV1.keySet()) norm1 += devV1.get(k) * devV1.get(k);
        for (int k : devV2.keySet()) norm2 += devV2.get(k) * devV2.get(k);
        
        return sclar / Math.sqrt(norm1 * norm2);
	}
}
