package Zork;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Condition {
	protected Boolean hasmode=false;
    protected String owner;
    protected String has;
    protected String object;
    protected String status;
    public Condition(){}
    public Condition(NodeList input){
    	   for (int k=0;k<input.getLength();k++){
	    	   if (input.item(k) instanceof Element){
	    	      Element temp;
	    	      temp=(Element)input.item(k);
	    	      if (temp.getNodeName()=="has"){
	    	    	  hasmode=true;
	    	    	  has=temp.getTextContent();
	    	    	//   System.out.println(temp.getTextContent());
	    	      }
	    	      if (temp.getNodeName()=="object"){
	    	    	  object=temp.getTextContent();  
	    	    	//   System.out.println(temp.getTextContent());
	    	      }
	    	      if (temp.getNodeName()=="status"){
	    	    	  status=temp.getTextContent();  
	    	    	//   System.out.println(temp.getTextContent());
	    	      }
	    	      if (temp.getNodeName()=="owner"){
	    	    	  owner=temp.getTextContent();  
	    	    	//   System.out.println(temp.getTextContent());
	    	      }
	    	   }}
    }
}
