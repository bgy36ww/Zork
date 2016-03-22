package Zork;

import java.util.ArrayList;

public class Room extends ZObject{
	protected ArrayList<Item> items;
	protected ArrayList<Triggers> triggers;
	protected ArrayList<container> containers;
	protected ArrayList<Border> borders;
	protected ArrayList<Creature> creatures;
	protected String type="regular";
    public Room(){
    	items=new ArrayList<Item>();
    	triggers=new ArrayList<Triggers>();
    	containers=new ArrayList<container>();
    	borders= new ArrayList<Border>();
    	creatures=new ArrayList<Creature>(); 
    			
    }
	
	
}
