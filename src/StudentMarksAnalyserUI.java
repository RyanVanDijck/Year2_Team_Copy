import gnu.jpdf.PDFJob;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

/*class to create a frame to display data */
class StudentMarksAnalyserUI extends JFrame{
	StudentMarksAnalyser main;
	TextArea output = new TextArea(); //A text area to display program output
	JPanel data = new JPanel(new BorderLayout());//A spare panel for now
	//JPanel user = new JPanel();
	JPanel header = new JPanel(new BorderLayout());
	JPanel buttonArea = new JPanel();
	JPanel button = new JPanel();
	JTable table;
	static String [][] dataArray;
	static String [] dataHeadings;

    static Statistics statistics;

	BufferedImage logo = (ImageIO.read(getClass().getResource("/SCANALYZERLOGO.png")));
	BufferedImage folder_Icon = (ImageIO.read(getClass().getResource("/FILE_ICON.png")));
	BufferedImage glass_Icon = (ImageIO.read(getClass().getResource("/GLASS_ICON.png")));


	JComboBox course = new JComboBox();
	//Handles Reading from the file
	ChooseHandler chooseHandler;

	//A menu bar to display options
	JMenuBar menuBar = new JMenuBar();
	JMenu FileMenu = new JMenu("File");
	//Allowing to open another file
	JMenuItem Open = new JMenuItem("Open");
	JMenu View = new JMenu("View");
	//Changing the theme
	JMenu Theme = new JMenu("Theme");
	JDialog Error = new JDialog();
	JMenu GraphMenu = new JMenu("Graph");
	//Drawing a graph
	JMenuItem Draw = new JMenuItem("Module");
	JMenuItem chooseStudent = new JMenuItem ("Student");
	//static JButton chooseFile;


	JButton showData = new JButton("Show Data");
	ActionListener Drawg = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			try{
			drawGraph();}
			catch(Exception e1){
				JOptionPane.showMessageDialog(Error ,"Please select a file ", "No File Error", JOptionPane.ERROR_MESSAGE);
			}




		}
	};

	ActionListener openMenu = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				openStudentMenu();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	};

	ActionListener returnData = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				addTable(dataArray,dataHeadings);
				showData.setVisible(true);
				add(showData);
		}
	};

	//The three themes
	JMenuItem system = new JMenuItem("System");
	ActionListener systemAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				repaint();
				revalidate();


			} catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException ex) {
				ex.printStackTrace();
			}
		}
	};
	JMenuItem Nimbus = new JMenuItem("Nimbus");
	ActionListener nimbusAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

				repaint();
				revalidate();



			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
				ex.printStackTrace();
			}
		}
	};

	JMenuItem Metal = new JMenuItem("Metal");
	ActionListener metalAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				repaint();
				revalidate();

			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
				ex.printStackTrace();
			}
		}
	};

	JMenuItem Module = new JMenuItem("Module Report");
	ActionListener ModuleAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				generateReport();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(Error ,"File could not be saved", "File Save Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	};

	JMenu Report = new JMenu("Report");






	public StudentMarksAnalyserUI(String name, StudentMarksAnalyser main) throws IOException{
		//Calling JFrame constructor
		super(name);
		//Adding Action Listeners
		this.main=main;
		chooseHandler = new ChooseHandler(main);
		Open.addActionListener(chooseHandler);
		Draw.addActionListener(Drawg);
		Nimbus.addActionListener(nimbusAction);
		system.addActionListener(systemAction);
		Metal.addActionListener(metalAction);
		chooseStudent.addActionListener(openMenu);
		//Adding themes
		Theme.add(system);
		Theme.add(Metal);
		Theme.add(Nimbus);
		//Adding items to menus
		View.add(Theme);
		FileMenu.add(Open);
		GraphMenu.add(Draw);
		GraphMenu.add(chooseStudent);
		//Adding menus to menubar
		menuBar.add(FileMenu);
		menuBar.add(View);
		menuBar.add(GraphMenu);


		Module.addActionListener(ModuleAction);
		Report.add(Module);
		menuBar.add(Report);
		this.setJMenuBar(menuBar);
		//Changing the logo of the program
		this.setIconImage(this.logo);

		setSize(1620,600);
		//user.setBackground(Color.decode("#0000"));
		data.setBackground(Color.decode("#0000"));
		header.setBackground((Color.decode("#0000")));
		buttonArea.setBackground((Color.decode("#0000")));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //The program will close when the window closes

		output.setEditable(false);
		JButton chooseFile = new JButton("Choose File");

		showData.setFont(new Font("Courier", Font.PLAIN,12));
		chooseFile.setFont(new Font("Courier", Font.PLAIN,12));
		chooseFile.setPreferredSize(new Dimension(300,150));
		showData.setPreferredSize(new Dimension(300,150));
		chooseFile.setIcon(new ImageIcon(folder_Icon));
		showData.setIcon(new ImageIcon(glass_Icon));
		showData.addActionListener(returnData);

		//select.setLayout(new GridLayout(4,1));
		//select.add(course);

		buttonArea.setLayout(new GridBagLayout());
		buttonArea.add(chooseFile);
		buttonArea.add(showData);

		chooseFile.setBorder(BorderFactory.createLineBorder(Color.BLACK, 30));
		showData.setBorder(BorderFactory.createLineBorder(Color.BLACK, 30));

		//user.setLayout(new GridLayout(1,2));
		//user.add(select);
		//user.add(output);
	

		//adding the objects
		//this.setLayout(new GridLayout(2,2));
		//this.add(visual);
		BufferedImage image = (ImageIO.read(getClass().getResource("/SCANALYZER.png")));
		JLabel picLabel = new JLabel(new ImageIcon(image));
		header.add(picLabel,BorderLayout.NORTH);

		this.add(header,BorderLayout.NORTH);
		//this.add(user);
		this.add(buttonArea,BorderLayout.CENTER);
		this.add(data,BorderLayout.SOUTH);


		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		chooseFile.addActionListener(chooseHandler);


	}
	//Drawing a graph for a chosen Module
	public void drawGraph() throws IOException{
		//Retrieving headings from choose handler object
		Object[] headings = Arrays.copyOfRange(chooseHandler.headings,3,chooseHandler.headings.length -1);
		//Allowing the user to enter a module though a drop down box
		String input= (String)JOptionPane.showInputDialog(this, "Choose a module to graph",
				"Choose Module",JOptionPane.PLAIN_MESSAGE,null,headings,headings[0]);
		if (input != null && !input.equals("")) {
			//Creating a frame to display graph
			GraphFrame graphFrame = new GraphFrame(chooseHandler.read.getData(), input,true);
		}
	}
	//Menu item for student
	public void openStudentMenu() throws IOException {
		ArrayList<Student> students = main.getRead().getData();
		//Retrieving headings from choose handler object
		Object[] headings = new Object[students.size()];

		for(int i=0;i<headings.length;i++)headings[i]=students.get(i).getRegNo();

		//Allowing the user to enter a module though a drop down box
		String input= (String)JOptionPane.showInputDialog(this, "Choose a student to graph",
				"Choose student",JOptionPane.PLAIN_MESSAGE,null,headings,headings[0]);
		if (input != null && !input.equals("")) {
			//Creating a frame to display graph
			GraphFrame graphFrame = new GraphFrame(chooseHandler.read.getData(), input,true);
		}
	}
	//Adding a table to the frame
	public void addTable(String[][] array, String[] headings){
			//Creating the table
			table = new JTable(array,headings);
			//adding table to the frame

			this.data.add(new JScrollPane(table), BorderLayout.SOUTH);
			this.remove(buttonArea);
			this.repaint();
			this.pack();
			setSize(1620,600);
			//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	public void generateReport() throws IOException {
		JFileChooser chooser = new JFileChooser(".");
		chooser.setDialogTitle("Save File");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF files", "pdf");
		chooser.setSelectedFile(new File("Report" + ".pdf"));
		chooser.setFileFilter(filter);
		int select = chooser.showSaveDialog(this);
		if (select == JFileChooser.APPROVE_OPTION) {


			FileOutputStream stream = new FileOutputStream(chooser.getSelectedFile());
			PDFJob job = new PDFJob(stream);
			Graphics g;
			GraphFrame temp;

			String[] modules = Arrays.copyOfRange(chooseHandler.headings, 3, chooseHandler.headings.length - 1);
			for (String module : modules) {
				temp = new GraphFrame(chooseHandler.read.getData(), module, false);
				temp.revalidate();
				temp.repaint();
				g = job.getGraphics();
				temp.getBar(g, 0.34);
				g.dispose();
				temp.dispose();

			}
			job.end();
			stream.close();
		}
	}


}
