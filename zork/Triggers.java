package Zork;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Triggers {
	protected String type="single";
    protected ArrayList<String> action;
	protected ArrayList<String> print;
    protected ArrayList<Condition> condition;
    protected Command command;
	
	public Triggers(){

      }
      public Triggers(NodeList input){
    	  action=new ArrayList<String>();
    	  print=new ArrayList<String>();
    	  condition=new ArrayList<Condition>();
    	  command=new Command();
    	    for (int k=0;k<input.getLength();k++){
    	    	   if (input.item(k) instanceof Element){
    	    	      Element temp;
    	    	      temp=(Element)input.item(k);
    	    	      if (temp.getNodeName()=="action"){
    	    	      action.add(temp.getTextContent());
    	    	    //  System.out.println("action:"+temp.getTextContent());
    	    	      }
    	    	      if (temp.getNodeName()=="type"){
    	    	      type=(temp.getTextContent());
    	    	   //   System.out.println("type:"+temp.getTextContent());
    	    	      }
    	    	      if (temp.getNodeName()=="print"){
    	    	    	  print.add(temp.getTextContent());
    	    	         // System.out.println("print:"+temp.getTextContent());
    	    	      }
    	    	      if (temp.getNodeName()=="condition"){
    	    	    	  Condition contemp=new Condition(input.item(k).getChildNodes());
    	 //condition   	    	  print.add(temp.getTextContent());
    	    	    	  condition.add(contemp);
    	    	         // System.out.println("condition:"+temp.getTextContent());
    	    	    	  
    	    	      }
    	    	      if (temp.getNodeName()=="command"){
    	    	    	  command.load(temp.getTextContent());
    	    	    //      System.out.println("command:"+temp.getTextContent());
    	    	      }
    	    	      
    	    	      
    	    	   
    	    	   }}
          
      }
}
