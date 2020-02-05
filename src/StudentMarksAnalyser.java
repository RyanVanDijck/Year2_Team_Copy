import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.Map;
import java.util.HashMap;
//main method to run program
public class StudentMarksAnalyser {
	StudentMarksAnalyserUI UI;
	private Read read;

	public StudentMarksAnalyser() throws IOException {
		UI = new StudentMarksAnalyserUI("CE291_TEAM_21",this);//creating a window to show content
		UI.setVisible(true);
	}

	public void setRead(Read read) {
		this.read = read;
	}

	public Read getRead(){
		return this.read;
	}

}
