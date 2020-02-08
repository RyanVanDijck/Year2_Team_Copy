import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        StudentMarksAnalyser main = new StudentMarksAnalyser();

        // String file = "data.csv"; //Name of file

        //format for testing stats
        /*
		Statistics stats = new Statistics();
		try{
		String SD = stats.getBest("CE141-4-AU").getExamNo();
		System.out.println(SD);
		}catch(NullPointerException npe){
		    System.out.println("No Value is found");
        }*/
    }
    public static void refresh(){

    }
}
