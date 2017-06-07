import java.io.FileReader;
import java.io.StreamTokenizer;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NumberReading {
    public static void main (String[] args) {
        try {
            FileReader fr = new FileReader("number.txt");
            StreamTokenizer st = new StreamTokenizer(fr);
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                if(st.ttype == StreamTokenizer.TT_NUMBER) {
                    System.out.println(st.nval); // nvalはdouble型
                }
            }
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println ("this file was not found.");
        } catch (IOException e) {
            System.out.println ("Error: " + e);
        }
    }
}