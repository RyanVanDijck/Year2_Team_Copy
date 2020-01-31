import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Color;

/*class to create a frame to display data */
class StudentMarksAnalyserUI extends JFrame{
	TextArea output = new TextArea(); //A text area to display program output
	JPanel visual = new JPanel(); // A panel to show visual aspects
	JPanel data = new JPanel(new BorderLayout());//A spare panel for now
	JPanel user = new JPanel();
	JPanel header = new JPanel(new BorderLayout());
	//JPanel  select = new JPanel();
	//JPanel button = new JPanel();

	BufferedImage hat = (ImageIO.read(getClass().getResource("/hat.png")));

	JComboBox course = new JComboBox();


	public StudentMarksAnalyserUI(String name) throws IOException{
		super(name);
		this.setIconImage(this.hat);
		setSize(800,500);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //The program will close when the window closes

		output.setEditable(false);
		JButton chooseFile = new JButton("Choose File");

		//select.setLayout(new GridLayout(4,1));
		//select.add(course);
		JLabel title = new JLabel("STUDENT MARKS ANALYSER");
		header.add(title,BorderLayout.NORTH);
		header.add(chooseFile,BorderLayout.SOUTH);
		title.setFont (title.getFont ().deriveFont (25.0f));
		title. setForeground(Color.red);
		title.setHorizontalAlignment(JLabel.CENTER);

		//user.setLayout(new GridLayout(1,2));
		//user.add(select);
		//user.add(output);
	

		//adding the objects
		//this.setLayout(new GridLayout(2,2));
		//this.add(visual);
		this.add(header,BorderLayout.NORTH);
		this.add(data,BorderLayout.SOUTH);
		//this.add(user);
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		chooseFile.addActionListener(new ChooseHandler(this));

	}

	public void addTable(String[][] array, String[] headings){
			JTable table = new JTable(array,headings);
			this.data.add(new JScrollPane(table), BorderLayout.SOUTH);
			this.repaint();
			this.pack();
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
