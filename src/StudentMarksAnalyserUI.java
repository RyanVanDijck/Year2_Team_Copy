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
	private StudentMarksAnalyser main;
	private JPanel data = new JPanel(new BorderLayout());
	private JPanel buttonArea = new JPanel();

	static boolean fileLoaded = false;
	static String [][] dataArray;
	static String [] dataHeadings;

    static Statistics statistics;

	//Handles Reading from the file
	private ChooseHandler chooseHandler;
	private JDialog Error = new JDialog();

	StudentMarksAnalyserUI(String name, StudentMarksAnalyser main) throws IOException{
		//Calling JFrame constructor
		super(name);
		//Adding Action Listeners
		this.main=main;
		chooseHandler = new ChooseHandler(main);
		fillGUI();

		setSize(1620,600);
		data.setBackground(Color.decode("#0000"));
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground((Color.decode("#0000")));
		buttonArea.setBackground((Color.decode("#0000")));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //The program will close when the window closes

		BufferedImage logo = (ImageIO.read(getClass().getResource("/SCANALYZERLOGO.png")));
		BufferedImage folder_Icon = (ImageIO.read(getClass().getResource("/FILE_ICON.png")));
		BufferedImage glass_Icon = (ImageIO.read(getClass().getResource("/GLASS_ICON.png")));
		BufferedImage report_Icon = (ImageIO.read(getClass().getResource("/GRAPH_LOGO.png")));

		//Changing the logo of the program
		this.setIconImage(logo);
		JButton chooseFile = new JButton("Choose File");
		JButton showData = new JButton("Show Data");
		JButton showReport = new JButton(("Show Report"));
		showReport.setFont(new Font("Courier", Font.PLAIN,12));
		showData.setFont(new Font("Courier", Font.PLAIN,12));
		chooseFile.setFont(new Font("Courier", Font.PLAIN,12));
		chooseFile.setPreferredSize(new Dimension(300,150));
		showData.setPreferredSize(new Dimension(300,150));
		showReport.setPreferredSize(new Dimension(300,150));
		chooseFile.setIcon(new ImageIcon(folder_Icon));
		showData.setIcon(new ImageIcon(glass_Icon));
		showReport.setIcon(new ImageIcon(report_Icon));
		showData.addActionListener(returnData);
		showReport.addActionListener(ModuleAction);

		buttonArea.setLayout(new GridBagLayout());
		buttonArea.add(chooseFile);
		buttonArea.add(showData);
		buttonArea.add(showReport);

		chooseFile.setBorder(BorderFactory.createLineBorder(Color.BLACK, 30));
		showData.setBorder(BorderFactory.createLineBorder(Color.BLACK, 30));
		showReport.setBorder(BorderFactory.createLineBorder(Color.BLACK, 30));

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

	private void fillGUI(){
		//A menu bar to display options
		JMenuBar menuBar = new JMenuBar();
		JMenu FileMenu = new JMenu("File");
		//Allowing to open another file
		JMenuItem Open = new JMenuItem("Open");
		JMenu View = new JMenu("View");
		//Changing the theme
		JMenu Theme = new JMenu("Theme");

		JMenu GraphMenu = new JMenu("Graph");
		JMenu Report = new JMenu("Report");
		//Drawing a graph
		JMenuItem Draw = new JMenuItem("Module");
		JMenuItem chooseStudent = new JMenuItem ("Student");
		JMenuItem system = new JMenuItem("System");
		JMenuItem Nimbus = new JMenuItem("Nimbus");
		JMenuItem Metal = new JMenuItem("Metal");
		JMenuItem Module = new JMenuItem("Module Report");

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
			new GraphFrame(chooseHandler.read.getData(), input,true);
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
			new GraphFrame(main.getRead().getData(), input,true);
		}
	}
	//Adding a table to the frame
	public void addTable(String[][] array, String[] headings){
		//Creating the table
		JTable table = new JTable(array,headings);
		//adding table to the frame

		this.data.add(new JScrollPane(table), BorderLayout.SOUTH);
		this.remove(buttonArea);
		this.repaint();
		this.pack();
		setSize(1620,600);
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
			int x=10;
			int y=20;
			int stepper=30;

			g=job.getGraphics();
			g.drawString("Easy Modules:",x,y);
			y+=20;
			g.drawString(""+statistics.getEasyModules(),x,y);
			y+=stepper;
			g.drawString("Hard Modules:",x,y);
			y+=20;
			g.drawString(""+statistics.getHardModules(),x,y);
			y+=stepper;
			g.drawString("Top 25% Student Easy Modules: ",x,y);
			y+=20;
			g.drawString(""+statistics.bestStudentEasyModules(main.getRead().getData().size()/4),x,y);
			y+=stepper;
			g.drawString("Top 25% Student Hard Modules: ",x,y);
			y+=20;
			g.drawString(""+statistics.bestStudentHardModules(main.getRead().getData().size()/4),x,y);
			y+=stepper;
			g.drawString("Bottom 25% Student Easy Modules: ",x,y);
			y+=20;
			g.drawString(""+statistics.worstStudentEasyModules(main.getRead().getData().size()/4),x,y);
			y+=stepper;
			g.drawString("Bottom 25% Student Hard Modules: ",x,y);
			y+=20;
			Font currentFont = g.getFont();
			Font newFont = currentFont.deriveFont(currentFont.getSize() * 0.5F);
			g.setFont(newFont);
			g.drawString(""+statistics.worstStudentHardModules(main.getRead().getData().size()/4),x,y);
			g.dispose();
			String[] modules = Arrays.copyOfRange(chooseHandler.headings, 3, chooseHandler.headings.length - 1);
			for (String module : modules) {
				temp = new GraphFrame(main.getRead().getData(), module, false);
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

	public void drawStudentData() throws IOException {
		String regNo = (String) JOptionPane.showInputDialog(this, "Please enter your reg number", "Choose RegNo",
				JOptionPane.PLAIN_MESSAGE, null, null, "regNo");
		boolean present = false;
		Student choice = null;
		for (Student student: main.getRead().getData()){
			if (regNo.equals( student.getRegNo())){
				System.out.println(statistics.getBestModuleByRegNo(regNo));
				System.out.println(statistics.getWorstModuleByRegNo(regNo));
				present = true;
				choice = student;
			}
		}
		if(!present){
			JOptionPane.showMessageDialog(this,"Reg Number not found", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else{
			BufferedImage logo = (ImageIO.read(getClass().getResource("/SCANALYZERLOGO.png")));
			JFrame frame = new JFrame();
			JLabel bestMark = new JLabel("Best Module: " + statistics.getBestModuleByRegNo(regNo));
			JLabel worstMark = new JLabel("Worst Module: " + statistics.getWorstModuleByRegNo(regNo));
			JLabel aveMark = new JLabel("Average Mark: " + choice.getAvgMark());
			JLabel passed = new JLabel("Status: " + (choice.getAvgMark() >= 40 ? "Pass": "Fail"));

			frame.setLayout(new GridLayout(4,1));
			frame.setTitle(regNo + " Data");
			frame.add(bestMark);
			frame.add(worstMark);
			frame.add(aveMark);
			frame.add(passed);
			frame.setIconImage(logo);
			frame.repaint();
			frame.pack();
			frame.setSize(500,500);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);

		}
	}

	private ActionListener Drawg = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				drawGraph();}
			catch(Exception e1){
				JOptionPane.showMessageDialog(Error ,"Please choose a file to analyse ", "No File Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	};

	private ActionListener openMenu = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				drawStudentData();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(Error ,"Please choose a file to analyse ", "No File Error", JOptionPane.ERROR_MESSAGE);
			}


		}


	};

	private ActionListener returnData = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (!fileLoaded){
					throw new Exception();
				}
				addTable(dataArray,dataHeadings);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(Error ,"Please choose a file to analyse", "No File Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	};



	//The three themes
	private ActionListener systemAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());


			} catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException ex) {
				ex.printStackTrace();
			}
		}
	};

	private ActionListener nimbusAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");




			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
				ex.printStackTrace();
			}
		}
	};

	private ActionListener metalAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
				ex.printStackTrace();
			}
		}
	};

	private ActionListener ModuleAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (!fileLoaded){
					throw new Exception();
				}
				generateReport();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(Error ,"Please choose a file to analyse", "No File Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	};


}
