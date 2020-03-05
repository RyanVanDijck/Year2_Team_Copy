import java.io.IOException;

//main method to run program
public class StudentMarksAnalyser {
	private StudentMarksAnalyserUI UI;
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
