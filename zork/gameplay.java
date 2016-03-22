package Zork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class gameplay{
	private ArrayList<Item> inventory;
	private Extract Zmap;
	private Scanner input;
	private Room currentroom;
	private boolean gameover=false;
	private ArrayList<Item> ava;
	public gameplay(Extract map){
         Zmap=map;
         currentroom=Zmap.roommap.get("Entrance");
         inventory=new ArrayList<Item>();
         input= new Scanner(System.in);
	}
	public void play(){
		System.out.println(currentroom.description);
		while(!gameover){
			System.out.print(">");
			String in = input.nextLine();
			Command command=new Command();
			command.load(in);
			exe(command.commands);
			
		}
		System.out.println("gameover");
	}
	public void exe(String in){
		checkoverwritten(in);
		String[] arr=in.split(" ");
        switch (arr[0]){
        case "n":Moveroom("north");
                 break;
        case "s":Moveroom("south");
                 break;
        case "e":Moveroom("east");
                 break;
        case "w":Moveroom("west");
                 break;
        case "i":openinventory();
                 break;
        case "open":open(arr[1]);
                 break;
        case "read":read(arr[1]);
                 break;
        case "drop":drop(arr[1]);
                 break;
        case "take":take(arr[1]);
                 break;
        case "put" :put(arr[1],arr[3]);
                 break;
        case "turn":turn(arr[2]);
                 break;
        case "attack":attack(arr[1],arr[3]);
                 break;
        case "look"  :	   System.out.println(currentroom.description);
                 break;
        case "Add"   :add(arr[1],arr[3]);
                 break;
        case "Delete":delete(arr[1]);
                 break;
        case "Update":update(arr[1],arr[3]);
                 break;
        case "Game":gameover();
                 break;
        default: System.out.println("Wrong command");
        }
		checktriggers();
		
		
		
	}
	public void checktriggers(){
		
		for (int i=0;i<currentroom.triggers.size();i++){
			if (check(currentroom.triggers.get(i))){
				activate(currentroom.triggers.get(i));
			
		}
		
		}
		for (int i=0;i<Zmap.triggermap.size();i++){

				if (check(Zmap.triggermap.get(i))){
					activate(Zmap.triggermap.get(i));
				
			}
		}
		
	}
	public void checkoverwritten(String check){
	
		for (int i=0;i<currentroom.triggers.size();i++){
			if (currentroom.triggers.get(i).command.commands.equals(check)){
				System.out.println("ovewrwritten");
				
				//need a check condition function;
				if (check(currentroom.triggers.get(i))){
					activate(currentroom.triggers.get(i));
					
				}
			}
		
		}
		for (int i=0;i<Zmap.triggermap.size();i++){
			if(Zmap.triggermap.get(i).command.commands.equals(check)){
				System.out.println("ovewrwritten");
				if (check(Zmap.triggermap.get(i))){
					activate(Zmap.triggermap.get(i));
				}
			}
		}
		
		
	}	
		
	public void activate(Triggers t){
		for (int i=0;i<t.action.size();i++){
			exe(t.action.get(i));
		}
		
	}
	public boolean check(Triggers t){
		for (int i=0;i<t.condition.size();i++){
			Item temp=Zmap.itemmap.get(t.condition.get(i).object);
			
			
			
			
			if (temp==null){
				return false;
			}else{
			if(t.condition.get(i).hasmode){


				if (t.condition.get(i).has.equals("yes")){


						if(!(temp.ownership.equals(t.condition.get(i).owner))){
						   return false;
						}
						
						}else{if((temp.ownership.equals(t.condition.get(i).owner))){
							   return false;
							}
							
					}
						
				}
				else{if (!(temp.status.equals(t.condition.get(i).status))){
					return false;
				}
					
				
			}
		}}
		return true;
	}
	public void Moveroom(String direction){
		boolean flag=false;
		for (int i=0;i<currentroom.borders.size();i++){
			String temp=currentroom.borders.get(i).direction;
			if(temp.equals(direction)){
				currentroom=Zmap.roommap.get(currentroom.borders.get(i).name);

				flag=true;
			}
		}
		if (!flag){
			System.out.println("can't go that way");
		}else
		{
			System.out.println(currentroom.description);
		}
		
	}
	public void openinventory(){
		if (inventory.size()==0){
			System.out.println("Inventory:empty");
		}
		for (int i=0;i<inventory.size();i++){
			if (i>0 & i<inventory.size()-1){
				System.out.print(",");
			}
			System.out.print(inventory.get(i));
		}
	}
	public void open(String ob){
		if (ob.equals("exit")){

			if (currentroom.type.equals("exit")){
				
				gameover=true;
			}
			else{System.out.println("error open exit");}
			
		}else{
			boolean flag=false;
			for (int i=0;i<currentroom.containers.size();i++){
				if (currentroom.containers.get(i).name.equals(ob)){
					currentroom.containers.get(i).status="Open";
					flag=true;
					int j=0;
					for (j=0;j<currentroom.containers.get(i).items.size();j++){
						System.out.println(currentroom.containers.get(i).items.get(j).name);
						ava.add(currentroom.containers.get(i).items.get(j));
					}
					if (j==0){
						System.out.println(ob+" is empty");
					}
				}
				
			}
			if (!flag){
				System.out.println("error open "+ob);
			}

		}
		
	}
	void read(String name){

		boolean flag=true;
		for (int i=0;i<currentroom.items.size();i++){

			if (currentroom.items.get(i).name.equals(name)){

				if (currentroom.items.get(i).writing!=null){
					System.out.println(currentroom.items.get(i).writing);
					flag=false;
				}else{
					System.out.println("error reading");
				}
				
			}
		}
		for (int i=0;i<inventory.size();i++){
			if (inventory.get(i).name.equals(name)){
				if (inventory.get(i).writing!=null){
					System.out.println(inventory.get(i).writing);
					flag=false;
				}else{
					System.out.println("error reading");
				}
				
			}
		}
		if (flag){
			System.out.println("can't find item");
		}
	}
   public void drop(String it){
	   boolean flag=true;
	   for (int i=0;i<inventory.size();i++){
		   if (inventory.get(i).name.equals(it)){
			   inventory.get(i).ownership=currentroom.name;
			   currentroom.items.add(inventory.get(i));
			   inventory.remove(i);
			   flag=false;
			   System.out.println("Item "+it+" droped");
		   }
	   }
	   if (flag){
		   System.out.println("can't find item");
	   }
   }
   public void take(String it){
	   boolean flag=true;
	   for (int i=0;i<currentroom.items.size();i++){

			if (currentroom.items.get(i).name.equals(it)){
                inventory.add(currentroom.items.get(i));
                currentroom.items.get(i).ownership="inventory";
                currentroom.items.remove(i);
                flag=false;
                System.out.println("Item "+it+" added to inventory");
			}
		}
	   if (flag){
		   System.out.println("can't find item");
	   }
   }
   
   
   public void put(String it,String con){
	   boolean flag=true;
	   for (int i=0;i<currentroom.containers.size();i++){

			if (currentroom.containers.get(i).name.equals(con)){
				for (int j=0;j<inventory.size();j++){
					if (inventory.get(j).name.equals(it)){
						currentroom.containers.get(i).items.add(inventory.get(j));
						inventory.get(j).ownership=currentroom.containers.get(i).name;
						inventory.remove(j);
					     flag=false;
					     System.out.println("Item "+it+" added to Container"+con);
					}
				}
				
                    
			}
		}
	   if (flag){
		   System.out.println("can't find item or container");
	   }
   }
   
   public void turn(String it){
	   boolean flag=true;
	   for (int j=0;j<inventory.size();j++){
         if (inventory.get(j).name.equals(it)){
		   inventory.get(j).turnon=true;
          System.out.println(inventory.get(j).print);
          exe(inventory.get(j).action);
          flag=false;
         }
	   }
	   if (flag){
		   System.out.println("can't find item or container");
	   }
	   }
   public void attack(String cre, String it){
	   boolean flag=true;
	   int k=0;
	   for (int j=0;j<inventory.size();j++){
	   if (inventory.get(j).name.equals(it)){
		   flag=false;
		   k=j;
		   break;

	   }
	   
	   }
	   if (flag){
		   System.out.println("can't find item");
	   }else{
		   for (int i=0;i<currentroom.creatures.size();i++){
		   if (currentroom.creatures.get(i).equals(cre)){
			 if (currentroom.creatures.get(i).vulnerability.equals(it)){
				 System.out.println(currentroom.creatures.get(i).attack.print);
				 for (int n=0;n<currentroom.creatures.get(i).attack.action.size();n++){
					 exe(currentroom.creatures.get(i).attack.action.get(n));
					 return;
				 }
			 }
		   
		   }
		   }
	   }
	   
	   System.out.println("attack failed");
   }
   public void add(String it,String pl){
	   Item item=new Item();
	   item.name=it;
	   item.ownership=pl;
	   if (Zmap.roommap.get(pl)!=null){
		   Zmap.roommap.get(pl).items.add(item);
	   }
	   if (Zmap.containermap.get(pl)!=null){
		   Zmap.containermap.get(pl).items.add(item);
	   }
	   Zmap.itemmap.put(it,item);
	   
	   
	   
   }
   public void delete(String it){
	   Zmap.itemmap.remove(it);
   }
   public void update(String ob,String st){
	   Zmap.itemmap.get(ob).status=st;
   }
   public void gameover(){
	   gameover=true;
   }
   
}
