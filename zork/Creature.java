package Zork;
import java.util.ArrayList;

public class Creature extends ZObject{
    protected String ownership;
    protected Attack attack;
    protected ArrayList<String> vulnerability;
    protected ArrayList<Triggers> triggers;
    public Creature(){
    	vulnerability=new ArrayList<String>();
    	triggers=new ArrayList<Triggers>();

    	
    }
    
}
