/**
 * 
 */
package Customer_Invitation.Customer_Invitation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * @author Trupti Kulkarni
 *
 */
public class Customer_invite {

	double longitude;
	private int user_id;
	double latitude;
	private String name;
	private double distance;
    static double dub_office_longitude,dub_office_latitude;
	public double getLongitude() {
		return longitude;
	}

		public static void findnearestCustomer(LinkedList<Customer_invite> Cust)
	{
		Iterator<Customer_invite> custIterator = Cust.iterator();
		while (custIterator.hasNext()) {
			Customer_invite customer= custIterator.next();			    
		    double angle1 = Math.acos(Math.sin(dub_office_latitude) * Math.sin(customer.latitude)
                    + Math.cos(dub_office_latitude) * Math.cos(customer.latitude) * Math.cos(dub_office_longitude - customer.longitude));
		    customer.distance=angle1 *60;
		    if(customer.distance<=100.00)
		    System.out.println("Send invitation for Food and Drinks to "+ customer.name +" whose located at "+customer.distance+" distance");
		    
		}
	}
		private static LinkedList<Customer_invite> parseCustomerJson(File f) {
			// TODO Auto-generated method stub
	        String readLine = "";
	        JSONParser parser = new JSONParser();
	        LinkedList<Customer_invite> Customer_list= new LinkedList<Customer_invite>();
	        try
	        {
	        BufferedReader b = new BufferedReader(new FileReader(f));
	        
			 while ((readLine = b.readLine()) != null) {
	             Object obj = parser.parse(readLine);
	             JSONObject jsonObject = (JSONObject) obj;
	            String name1 = (String) jsonObject.get("name");
	            String longi = (String) jsonObject.get("longitude");
	            String latitude = (String) jsonObject.get("latitude");
	            Customer_invite customer= new Customer_invite();
	         //To convert Decimal to radians 
	           customer.latitude= Math.toRadians((Double.parseDouble(latitude)));
	           customer.longitude=Math.toRadians((Double.parseDouble(longi)));
	           customer.name=name1;
	           Customer_list.add(customer);
	         }
			
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        return Customer_list;
		}
	
	
	public Customer_invite() {
			super();
			this.longitude = 0;
			this.user_id = 0;
			this.latitude = 0;
			this.name = "";
		}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		  
		  dub_office_longitude= Math.toRadians(53.339428);
		  dub_office_latitude=Math.toRadians(-6.257664);
		  LinkedList<Customer_invite> Customer_list= new LinkedList<Customer_invite>();
		  try {
	          File f = new File("H:\\Customer_Text_JSON\\19896c50afa89ad4dec3-6c11047887a03483c50017c1d451667fd62a53ca\\gistfile1.txt");
	          Customer_list = parseCustomerJson(f);
	          findnearestCustomer(Customer_list);
		  }
		  catch (Exception e) {
	            e.printStackTrace();
	        }
		  }
}


