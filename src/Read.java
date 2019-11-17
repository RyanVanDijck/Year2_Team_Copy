import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Read {
    String file = "src/data.csv";
    String cline;
    BufferedReader br = new BufferedReader(new FileReader(file));

    public Read() throws IOException {
        while ((cline = br.readLine()) != null){
            String[] entry =  cline.split(",");
            for (String i: entry){
                System.out.print(i + " ");
            }
            System.out.print("\n");
        }
    }
    public static void main(String args[]) throws IOException {
        Read read = new Read();
    }
}
