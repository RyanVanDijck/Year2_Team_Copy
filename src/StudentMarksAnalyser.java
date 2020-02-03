import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.Map;
import java.util.HashMap;
//main method to run program
public class StudentMarksAnalyser {

    public static void main(String[] args) throws IOException{
      try {
  			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
  		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}

		StudentMarksAnalyserUI window = new StudentMarksAnalyserUI("CE291_TEAM_21");//creating a window to show content
		// String file = "data.csv"; //Name of file
        window.setVisible(true);
		/*
        //format for testing stats
		Statistics stats = new Statistics();
		double SD = stats.getSD("CE101-4-SP");
		System.out.println(SD);
*/

	}
}
