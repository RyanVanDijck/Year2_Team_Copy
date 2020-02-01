import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	ChooseHandler chooseHandler;

	JMenuBar menuBar = new JMenuBar();
	JMenu FileMenu = new JMenu("File");
	JMenuItem Open = new JMenuItem("Open");
	JMenu GraphMenu = new JMenu("Graph");
	JMenuItem Draw = new JMenuItem("Draw");

	ActionListener Drawg = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			drawGraph();
		}
	};






	public StudentMarksAnalyserUI(String name) throws IOException{
		super(name);
		chooseHandler = new ChooseHandler(this);
		Open.addActionListener(chooseHandler);
		Draw.addActionListener(Drawg);
		FileMenu.add(Open);
		GraphMenu.add(Draw);
		menuBar.add(FileMenu);
		menuBar.add(GraphMenu);
		this.setJMenuBar(menuBar);
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
		this.add(data,BorderLayout.NORTH);
		this.add(user);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		chooseFile.addActionListener(chooseHandler);

	}

	public void drawGraph(){
		Object[] headings = chooseHandler.headings;
		String input= (String)JOptionPane.showInputDialog(this, "Choose a module to graph",
				"Choose Module",JOptionPane.PLAIN_MESSAGE,null,headings,headings[0]);
		GraphFrame graphFrame = new GraphFrame(chooseHandler.read.getData(),input);
	}

	public void addTable(String[][] array, String[] headings){
			JTable table = new JTable(array,headings);
			this.data.add(new JScrollPane(table), BorderLayout.SOUTH);
			this.repaint();
			this.pack();
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
