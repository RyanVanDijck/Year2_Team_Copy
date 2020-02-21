import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            if (System.getProperty("os.name").equals("Linux")){
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            }
            else {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }

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
}
