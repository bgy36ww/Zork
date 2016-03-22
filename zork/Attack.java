package Zork;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.ArrayList;


public class Attack {
    protected ArrayList<String> action;
	protected String print;
	
	public Attack(){

      }
      public Attack(NodeList input){
    	  action=new ArrayList<String>();
    	    for (int k=0;k<input.getLength();k++){
    	    	   if (input.item(k) instanceof Element){
    	    	      Element temp;
    	    	      temp=(Element)input.item(k);
    	    	      if (temp.getNodeName()=="action"){
    	    	      action.add(temp.getTextContent());
    	    	      }else{
    	    	    	  print=temp.getTextContent();
    	    	      }
    	    	      
    	    	      System.out.println("attack: "+temp.getTextContent());
    	    	      
    	    	   
    	    	   }}
          
      }
}
