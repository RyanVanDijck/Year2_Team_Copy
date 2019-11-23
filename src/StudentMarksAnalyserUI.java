import javax.swing.*;
import java.awt.*;
/*class to create a frame to display data */
class StudentMarksAnalyserUI extends JFrame{
	TextArea output = new TextArea(); //A text area to display program output
	JPanel visual = new JPanel(); // A panel to show visual aspects
	JPanel data = new JPanel();//A spare panel for now
	JPanel user = new JPanel();
	JPanel  select = new JPanel();
	JPanel button = new JPanel();

	JComboBox course = new JComboBox();


	public StudentMarksAnalyserUI(String name){
		super(name);
		setSize(800,800);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //The program will close when the window closes

		output.setEditable(false);

		select.setLayout(new GridLayout(4,1));
		select.add(course);

		user.setLayout(new GridLayout(1,2));
		user.add(select);
		user.add(output);

		//adding the objects
		this.setLayout(new GridLayout(3,2));
		this.add(visual);
		this.add(data);
		this.add(user);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

	}
}
