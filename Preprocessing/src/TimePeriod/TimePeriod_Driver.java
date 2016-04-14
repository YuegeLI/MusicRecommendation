package TimePeriod;

public class TimePeriod_Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filePath = "./data/USData.txt";
//		String filePath = "./data/test_userid-timestamp-artid-artname-traid-traname.txt";
		LoadData ld = new LoadData(filePath);
		System.out.println("Done");

	}

}
