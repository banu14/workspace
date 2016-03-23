import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;


public class Test {
	int [] release_arr = {};
	Thread thread = new Thread();
	static HashMap <String, Integer> release_map = new HashMap<String,Integer>();
	static HashMap <String, Integer> hold_map = new HashMap<String,Integer>();
	
	static HashMap <Integer, Integer> ptrs_map = new HashMap<Integer,Integer>();
	static ArrayList <Thread> thread_arr = new ArrayList<Thread>();
	
	static int start_ptr = 0;
	static int lastest_ptr = 0;
	static int req_seats = 4;
	static int row_number = 1;
	static  int START = 1;
    static int END;
static  Random random = new Random();
static ArrayList<Integer> numbersList = new ArrayList <Integer>();
	static HashMap <String, Integer> resrv_map = new HashMap<String,Integer>();
	 
	 private static void intilializeRandomNos(){
		 
	        for(int x=1;x<=10;x++)
	            numbersList.add(x);
	        Collections.shuffle(numbersList);
}
	
	 public static int generateRandomNo()
	 {
		 int randno = numbersList.get(lastest_ptr);
		 lastest_ptr++;
		 return randno;
	 }
	public static void main(String [] args )
	{
		intilializeRandomNos();
		for (int i =0;i<10;i++)
		{
			System.out.println("Random Nos----------->"+generateRandomNo());
		}
		@SuppressWarnings("unused")
		RunnableJob runnableJob = new RunnableJob();
		
		Thread thread1 = new Thread(runnableJob);
		thread1.setName("thread1");
		thread1.start();
		
		Thread thread2 = new Thread(runnableJob);
		thread2.setName("thread2");
		thread2.start();
		thread_arr.add(thread1);
		thread_arr.add(thread2);
		
		ptrs_map.put(1,0);
		ptrs_map.put(2,0);
		/*release_map.put("1",1);
		release_map.put("2",2);
		release_map.put("7",7);
		release_map.put("8",8);*/
		//resrv_map.put(9, 9);
		//resrv_map.put(10,10);
		getReservedSeats();
		
	}
	
	
	
	
	public static void getReservedSeats()
	{
		if(release_map.size() >= req_seats)
		{
			
			@SuppressWarnings("rawtypes")
			Iterator it = (Iterator) release_map.entrySet().iterator();
			while (it.hasNext())
			{
			    @SuppressWarnings("rawtypes")
				Map.Entry pair = (Map.Entry)it.next();
			        
				Boolean status = true;
				int item = (int) pair.getValue();
				start_ptr = item;
				status = getSeats(req_seats,item+1);
				if(status)
				{
					break;
				}
			}
			lastest_ptr = req_seats + start_ptr+1;
			System.out.println("Start Pointer "+start_ptr);
		}
		else
		{
			start_ptr = lastest_ptr+1;
			Boolean status = getSeats(req_seats, lastest_ptr+1);
		}
		System.out.println("Start Pointer ====="+start_ptr + "Latest Ptr======"+lastest_ptr);
	}
	public static Boolean  getSeats(int noOfSeats,int item)
	{
		
		Boolean status = true;
		
		
	
		
		
		
		for (int j=0;j<noOfSeats-1;j++)
		{
			String value = Integer.toString(item);
			System.out.println("Value =========="+release_map.containsKey(value));
		
			
			if (resrv_map.containsKey(value) || release_map.containsKey(value) ||  hold_map.containsKey(value))
			{
				status = false;
				break;
			}
			item++;
		}
		if(status)
		{
			lastest_ptr = item +1;
		}
		return status;
	}

}
