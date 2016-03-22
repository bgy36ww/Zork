package Zork;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class Extract {
	public HashMap<String, Room> roommap;
	public HashMap<String, Item> itemmap;
	public HashMap<String, container> containermap; 
	public HashMap<String, Creature> creaturemap;
	public ArrayList<Triggers> triggermap;
	
	public Extract(String filename){
//public Extract(String XMLFileName) {
try {
File f=new File(filename);
if (!f.canRead()){
	 //System.out.println("Error opening");
	 return;
}
	//File f = new File(XMLFileName);
 roommap=new HashMap<String, Room>();
 itemmap=new HashMap<String, Item>();
 containermap=new HashMap<String, container>();
 creaturemap=new HashMap<String,Creature>();
 triggermap=new ArrayList<Triggers>();



DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
DocumentBuilder builder = factory.newDocumentBuilder();
Document doc = builder.parse(f);
NodeList nl = doc.getElementsByTagName("room");
for (int i = 0; i < nl.getLength(); i++) {
	NodeList child=nl.item(i).getChildNodes();
	Room roomtemp=new Room();
	for (int j =0; j<child.getLength();j++){

       if (child.item(j) instanceof Element){
   		Element temp;
   		temp=(Element)child.item(j);
   		if (temp.getNodeName()=="name"){  
   			roomtemp.name=temp.getTextContent();
            //System.out.println("name: "+temp.getTextContent());
       }
   		if (temp.getNodeName()=="description"){
   			roomtemp.description=temp.getTextContent();
            //System.out.println("description: "+temp.getTextContent());
   		}
   		if (temp.getNodeName()=="status"){
   			roomtemp.status=temp.getTextContent();
            //System.out.println("status: "+temp.getTextContent());
   		}
   		if (temp.getNodeName()=="type"){
   			roomtemp.type=temp.getTextContent();
            //System.out.println("type: "+temp.getTextContent());
   		}	
   		
       }
       
	}
       
       
       
       
       
       
	        NodeList itemnode=((Element)child).getElementsByTagName("item");//item

            for (int k=0;k<itemnode.getLength();k++){
           if (itemnode.item(k) instanceof Element){	
	   		Item itemtemp=new Item();
	   		Element contemp=(Element)itemnode.item(k);
	   		itemtemp.name=contemp.getTextContent();
	   		itemtemp.ownership=roomtemp.name;
	   		roomtemp.items.add(itemtemp);
         //  System.out.println("item: "+contemp.getTextContent());
           itemmap.put(itemtemp.name, itemtemp);
           } 

            }	


   			
   			NodeList containernode=((Element)child).getElementsByTagName("container");//container
            for (int k=0;k<containernode.getLength();k++){

                if (containernode.item(k) instanceof Element){


    	   		container containertemp=new container();
    	   		Element contemp=(Element)containernode.item(k);
    	   		containertemp.name=contemp.getTextContent();
    	   		containertemp.ownership=roomtemp.name;
    	   		roomtemp.containers.add(containertemp);
    	   		containermap.put(containertemp.name, containertemp);
                //System.out.println("container: "+contemp.getTextContent());
                }    }	
   			
            
	        NodeList creaturenode=((Element)child).getElementsByTagName("creature");//creature
            for (int k=0;k<creaturenode.getLength();k++){
           if (creaturenode.item(k) instanceof Element){	
	   		Creature creaturetemp=new Creature();
	   		Element contemp=(Element)creaturenode.item(k);
	   		creaturetemp.name=contemp.getTextContent();
	   		creaturetemp.ownership=roomtemp.name;
	   		roomtemp.creatures.add(creaturetemp);
	   		creaturemap.put(creaturetemp.name, creaturetemp);
           //System.out.println("creature: "+creaturetemp.name);
           }    }	

            
            ////////////////////////////////////////////////////////////////////////////////////////////////
            /////////////////////////TRIGGERS//////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////
	        NodeList triggernode=((Element)child).getElementsByTagName("trigger");//trigger
            for(int k=0;k<triggernode.getLength();k++){
            	if (creaturenode.item(k) instanceof Element){
            		Triggers tiggertemp=new Triggers(creaturenode.item(k).getChildNodes());
            		roomtemp.triggers.add(tiggertemp);
            		
           
           
           }}        
/*}}
   			NodeList containernode=((Element)child).getElementsByTagName("container");
   			//System.out.println(containernode.getLength());
            for (int k=0;k<containernode.getLength();k++){

                if (containernode.item(k) instanceof Element){

                //System.out.println("22");	
    	   		container containertemp=new container();
    	   		Element contemp=(Element)containernode.item(k);
    	   		containertemp.name=contemp.getTextContent();
    	   		containertemp.ownership=roomtemp.name;
                //System.out.println("container: "+contemp.getTextContent());
                }    }
            
   			Reference
   		*/	
   			
   			

            
            
            NodeList bordernode=((Element)child).getElementsByTagName("border");

           for (int k=0;k<bordernode.getLength();k++){
          if (bordernode.item(k) instanceof Element){
           NodeList bordernode2=bordernode.item(k).getChildNodes();
           Border bordertemp=new Border();
           for (int kk=0;kk<bordernode2.getLength();kk++){
              if (bordernode2.item(kk) instanceof Element){
              Element temp2;
              temp2=(Element)bordernode2.item(kk);
              if (temp2.getNodeName()=="name"){
                 bordertemp.name=temp2.getTextContent();
                 //System.out.println("name: "+temp2.getTextContent());
              }
              if (temp2.getNodeName()=="direction"){
                  bordertemp.direction=temp2.getTextContent();
                  //System.out.println("direction: "+temp2.getTextContent());
               }
            	  
            	  
              }
           }
           if (bordertemp!= null){  
    		roomtemp.borders.add(bordertemp);
           }}}
            
     
           
           
           
           roommap.put(roomtemp.name, roomtemp);
           
           
           
   		
   		
   		

   		/*
   		if (temp.getNodeName()=="creature"){
   			Creature creaturetemp=new Creature();
   			
   			roomtemp.creatures.add(creaturetemp);
            //System.out.println("creature: "+temp.getTextContent());
   		}*/
   	/*	if (temp.getNodeName()=="trigger"){
   			Triggers triggertemp=new Triggers();
   			
   			roomtemp.triggers.add(triggertemp);
            //System.out.println("trigger: "+temp.getTextContent());
   		}
   		
   		*/
   		
   	//	if (temp.getNodeName()=="container"){    for reference
     //        //System.out.println(temp.getTextContent());
    //      }
       
    }






//container
nl = doc.getElementsByTagName("container");
container containertemp=null;
for (int i = 0; i < nl.getLength(); i++) {
	NodeList child=nl.item(i).getChildNodes();	


	for (int j =0; j<child.getLength();j++){

       if (child.item(j) instanceof Element){
   		Element temp;
   		temp=(Element)child.item(j);
   		if (temp.getNodeName()=="name"){  
   			containertemp=containermap.get(temp.getTextContent());
   			if (containertemp==null){
   	            containertemp=new container();
   	            containertemp.name=temp.getTextContent();
   			}

            //System.out.println("name: "+containertemp.name);
       }

   		if (temp.getNodeName()=="description"){
   			containertemp.description=temp.getTextContent();
            //System.out.println("description: "+temp.getTextContent());
   		}
   		if (temp.getNodeName()=="status"){
   			containertemp.status=temp.getTextContent();
            //System.out.println("status: "+temp.getTextContent());
   		}
   		if (temp.getNodeName()=="trigger"){

    		Triggers tiggertemp=new Triggers(temp.getChildNodes());
    		triggermap.add(tiggertemp);
    		
   
   

	
	}
     
   		
       }

       
	}
    ///////////////////////////////////////////
    ////////////////triggers//////////////////
	
    NodeList acceptnode=((Element)child).getElementsByTagName("accept");
    for (int k=0;k<acceptnode.getLength();k++){
   if (acceptnode.item(k) instanceof Element){
      Element temp;
      temp=(Element)acceptnode.item(k);
      containertemp.accept.add(temp.getTextContent());
      //System.out.println("accept: "+temp.getTextContent());
      
   
   }}
    
    NodeList itemnode=((Element)child).getElementsByTagName("item");//item

    for (int k=0;k<itemnode.getLength();k++){
   if (itemnode.item(k) instanceof Element){	
		Item itemtemp=new Item();
		Element contemp=(Element)itemnode.item(k);
		itemtemp.name=contemp.getTextContent();
		itemtemp.ownership=containertemp.name;
   //System.out.println("item: "+contemp.getTextContent());
   itemmap.put(itemtemp.name, itemtemp);
   } 

    }	
    
    
	
	
	

} 








//creature treat

nl = doc.getElementsByTagName("creature");
Creature creaturetemp=null;
for (int i = 0; i < nl.getLength(); i++) {
	NodeList child=nl.item(i).getChildNodes();	


	for (int j =0; j<child.getLength();j++){

       if (child.item(j) instanceof Element){
   		Element temp;
   		temp=(Element)child.item(j);
   		if (temp.getNodeName()=="name"){  
   			creaturetemp=creaturemap.get(temp.getTextContent());
   			if (creaturetemp==null){
   	            creaturetemp=new Creature();
   	            creaturetemp.name=temp.getTextContent();
   			}
            //System.out.println("name: "+temp.getTextContent());
       }
   		if (temp.getNodeName()=="description"){
   			creaturetemp.description=temp.getTextContent();
            //System.out.println("description: "+temp.getTextContent());
   		}
   		if (temp.getNodeName()=="status"){
   			creaturetemp.status=temp.getTextContent();
            //System.out.println("status: "+temp.getTextContent());
   		}
   		if (temp.getNodeName()=="trigger"){

            		Triggers tiggertemp=new Triggers(temp.getChildNodes());
            		triggermap.add(tiggertemp);
            		
           
           

   		
   		}
   		
       }
       
	}
	//triggers
	
	
	
	
	
	
	//attack
    NodeList attacknode=((Element)child).getElementsByTagName("attack");
    if (attacknode.getLength()>0){
    Attack attacktemp=new Attack(attacknode);
    creaturetemp.attack=attacktemp;}

	
	//vulnerability
    NodeList vulnerabilitynode=((Element)child).getElementsByTagName("vulnerability");
    for (int k=0;k<vulnerabilitynode.getLength();k++){
   if (vulnerabilitynode.item(k) instanceof Element){
      Element temp;
      temp=(Element)vulnerabilitynode.item(k);
      creaturetemp.vulnerability.add(temp.getTextContent());
      //System.out.println("vulnerability: "+temp.getTextContent());
      
   
   }}
	
	
	
	
	
	
	

} 













//item treat
nl = doc.getElementsByTagName("item");
Item itemtemp=null;
for (int i = 0; i < nl.getLength(); i++) {
	NodeList child=nl.item(i).getChildNodes();	
    Element temp;
    NodeList tempname=((Element)child).getElementsByTagName("name");
    if (tempname.getLength()>0){
    temp=(Element)tempname.item(0);
    
    
    itemtemp=itemmap.get(temp.getTextContent());
	if (itemtemp==null){
		itemtemp=new Item();
		itemtemp.name=temp.getTextContent();
	}
    //System.out.println("name: "+temp.getTextContent());
    }
	for (int j =0; j<child.getLength();j++){

       if (child.item(j) instanceof Element){
   		temp=(Element)child.item(j);
   		if (temp.getNodeName()=="description"){
   			itemtemp.description=temp.getTextContent();
            //System.out.println("description: "+temp.getTextContent());
   		}
   		if (temp.getNodeName()=="status"){
   			itemtemp.status=temp.getTextContent();
            //System.out.println("status: "+temp.getTextContent());
   		}
   		if (temp.getNodeName()=="writing"){
   			
   			itemtemp.writing=temp.getTextContent();
            //System.out.println("writing: "+temp.getTextContent());
   		}	
   		if (temp.getNodeName()=="trigger"){

    		Triggers tiggertemp=new Triggers(temp.getChildNodes());
    		triggermap.add(tiggertemp);
    		
   
   

	
	}
   		if (temp.getNodeName()=="turnon"){
   			
   			itemtemp.turnon=true;
   			//System.out.println(temp.getChildNodes().getLength());
   			for (int e=0;e<temp.getChildNodes().getLength();e++){
   			if (temp.getChildNodes().item(e) instanceof Element){
   			if (((Element)temp.getChildNodes().item(e)).getNodeName()=="print"){
   				
   				itemtemp.print=((Element)temp.getChildNodes().item(e)).getTextContent();
   	            //System.out.println("turnon.print: "+itemtemp.print);
   				}
   			if (((Element)temp.getChildNodes().item(e)).getNodeName()=="action"){
   			itemtemp.action=((Element)temp.getChildNodes().item(e)).getTextContent();
            //System.out.println("turnon.action: "+itemtemp.action);
   			}
   			}}

   		}	
   		
       }
       
	}

} 











}





catch (Exception e) {
e.printStackTrace();
}
//int flag=1;
//while(flag==1){
	   
	
}}


