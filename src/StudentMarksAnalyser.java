import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
//main method to run program
public class StudentMarksAnalyser {
	StudentMarksAnalyserUI UI;
	private Read read;

	public StudentMarksAnalyser() throws IOException {
		UI = new StudentMarksAnalyserUI("CE291_TEAM_21",this);//creating a window to show content
		UI.setVisible(true);
		Read read=new Read();
		Statistics stats = new Statistics(read);
		ArrayList<Student> students = stats.rankStudents("CE101-4-FY");
		for(Student s:students){
			System.out.println(s.getMark("CE101-4-FY"));
		}
		System.out.println(stats.getBestStudent("CE101-4-FY").getMark("CE101-4-FY"));
		System.out.println(stats.getWorstStudent("CE101-4-FY").getMark("CE101-4-FY"));
		Student[] best = stats.getBestStudents("CE101-4-FY",5);
		for(Student s:best){
			System.out.println(s.getMark("CE101-4-FY"));
		}
		Student[] worst = stats.getWorstStudents("CE101-4-FY",10);
		for(Student s:worst){
			System.out.println(s.getMark("CE101-4-FY"));
		}
	}

	public void setRead(Read read) {
		this.read = read;
	}

	public Read getRead(){
		return this.read;
	}

}
