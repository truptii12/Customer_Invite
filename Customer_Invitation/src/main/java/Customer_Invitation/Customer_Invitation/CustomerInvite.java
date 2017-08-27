/**
 * 
 */
package Customer_Invitation.Customer_Invitation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * @author Trupti Kulkarni
 *
 */
public class CustomerInvite {

	double longitude;
	double latitude;
	private String name;
	private double distance;
    static double dub_office_longitude,dub_office_latitude;
	public double getLongitude() {
		return longitude;
	}

		private static void findnearestCustomer(LinkedList<CustomerInvite> Cust)
	{
		Iterator<CustomerInvite> custIterator = Cust.iterator();
		try{
		while (custIterator.hasNext()) {
			CustomerInvite customer= custIterator.next();			    
		    double angle1 = Math.acos(Math.sin(dub_office_latitude) * Math.sin(customer.latitude)
                    + Math.cos(dub_office_latitude) * Math.cos(customer.latitude) * Math.cos(dub_office_longitude - customer.longitude));
		    customer.distance=angle1 *60;
		    if(customer.distance<=100.00)
		    System.out.println("Send invitation for Food and Drinks to "+ customer.name +" whose located at "+customer.distance+" distance");
		}
		}
		catch(ArithmeticException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
		private static LinkedList<CustomerInvite> parseCustomerJson() {
			// TODO Auto-generated method stub
	        String readLine = "";
	        JSONParser parser = new JSONParser();
	        LinkedList<CustomerInvite> Customer_list= new LinkedList<CustomerInvite>();
	        
	        try
	        {
	        	File f = new File("H:\\Customer_Text_JSON\\19896c50afa89ad4dec3-6c11047887a03483c50017c1d451667fd62a53ca\\gistfile1.txt");
	        BufferedReader b = new BufferedReader(new FileReader(f));
	        
			 while ((readLine = b.readLine()) != null) {
	             Object obj = parser.parse(readLine);
	             JSONObject jsonObject = (JSONObject) obj;
	            String name1 = (String) jsonObject.get("name");
	            String longi = (String) jsonObject.get("longitude");
	            String latitude = (String) jsonObject.get("latitude");
	            CustomerInvite customer= new CustomerInvite();
	         //To convert Decimal to radians 
	           customer.latitude= Math.toRadians((Double.parseDouble(latitude)));
	           customer.longitude=Math.toRadians((Double.parseDouble(longi)));
	           customer.name=name1;
	           Customer_list.add(customer);
	         }
			
	        }
	        catch(ClassCastException e)
	        {
	        	e.printStackTrace();
	        }
	        catch(NumberFormatException fe){
	        	fe.printStackTrace();
	        }
	        catch(FileNotFoundException fnf)
	        {
	        	fnf.printStackTrace();
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        return Customer_list;
		}
	
	
	public CustomerInvite() {
			super();
			this.longitude = 0;
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
		  LinkedList<CustomerInvite> Customer_list= new LinkedList<CustomerInvite>();
		  try {
	          
	          Customer_list = parseCustomerJson();
	          findnearestCustomer(Customer_list);
		  }
		  catch (Exception e) {
	            e.printStackTrace();
	        }
		  }
}


