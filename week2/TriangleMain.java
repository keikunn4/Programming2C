import java.io.*;

public class TriangleMain {
	  public static String getString() throws IOException {
	        try{
	            InputStreamReader isr = new InputStreamReader(System.in);
	            BufferedReader br = new BufferedReader(isr);
	            String s = br.readLine();
	            return s;
	        }catch(IOException e){
	            System.out.println ( "Error: " + e );
	            return null;
	        }
	    }
	    
	    public static int getInt() throws IOException {
	        try{
	            String s = getString();
	            int num = Integer.parseInt(s);
	            return num;
	        }catch(IOException e){
	            System.out.println ( "Error: " + e );
	            return 0;
	        }
	    }
	    
	    public static void main(String[] args) throws IOException{
	        int inputNum;
	        System.out.print("input n: ");
	        System.out.flush();
	        inputNum = getInt();
	        System.out.println(Triangle.triangle(inputNum));
	        
	    }

}
