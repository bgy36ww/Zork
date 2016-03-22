package Zork;
import java.util.ArrayList;


public class container extends ZObject{
	 protected String ownership;
     protected ArrayList<String> accept;
     protected ArrayList<Item> items;
     protected ArrayList<Triggers> triggers;
	public container(){
		 accept = new ArrayList<String>();
		 items=new ArrayList<Item>();
		 triggers = new ArrayList<Triggers>();
	}
	
	

}
