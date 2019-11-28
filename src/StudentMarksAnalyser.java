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
  		} catch (ClassNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (InstantiationException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (IllegalAccessException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (UnsupportedLookAndFeelException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}

      StudentMarksAnalyserUI window = new StudentMarksAnalyserUI("Student Marks");//creating a window to show content
		// String file = "data.csv"; //Name of file
        window.setVisible(true);
	}
}
