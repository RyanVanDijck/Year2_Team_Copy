import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

        Statistics stats = new Statistics();
        try{
            stats.getStudentBest("2500001");
        }catch(NullPointerException npe){

        }
}
}
