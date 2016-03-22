package Zork;





public class Zork {
    public static void main(String[] argv){
    	if (argv.length==0) {
    		System.out.println("need file name");
    	}
    	else{
    		Extract XMLmap = new Extract(argv[0]);
            new gameplay(XMLmap).play();    		
    	}
    }
}
