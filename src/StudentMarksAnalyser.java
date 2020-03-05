import java.io.IOException;

//main method to run program
public class StudentMarksAnalyser {
	private StudentMarksAnalyserUI UI;
	private Read read;

	public StudentMarksAnalyser() throws IOException {
		UI = new StudentMarksAnalyserUI("CE291_TEAM_21",this);//creating a window to show content
		UI.setVisible(true);

		Read r = new Read();
		Statistics stats = new Statistics(r);

		System.out.println(stats.getBestModuleByRegNo("2500001"));
		System.out.println(stats.getWorstModuleByRegNo("2500023"));

		System.out.println(stats.getEasyModules());
		System.out.println(stats.getHardModules());
	}

	public void setRead(Read read) {
		this.read = read;
	}

	public Read getRead(){
		return this.read;
	}

}
