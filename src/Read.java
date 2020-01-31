import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;


//Reading from the CSV file
public class Read {
	private JFileChooser choose = new JFileChooser(".");
    private ArrayList<Student> data = new ArrayList<Student>(); //list of data
    private ArrayList<String> headings = new ArrayList<String>(); //list of headings

    public Read() throws IOException {
        File file = Choose();
        readData(file);
        GraphFrame g = new GraphFrame(data,"CE101-4-FY");
	}

	//Method to transfer information from the file to instances of the student class
	private File Choose(){
		FileNameExtensionFilter f = new FileNameExtensionFilter("Comma Separated Value(.csv)","csv");
		choose.addChoosableFileFilter(f);
		choose.addChoosableFileFilter(new FileNameExtensionFilter("Text File(.txt)","txt"));
		choose.setFileFilter(f);
		int returnVal = choose.showOpenDialog(null); //Opening the file chooser with an int to show if file is openable
		/*
		 * NOTE
		 * dataFile initialised by using empty string as there is no empty constructor
		 * and File cannot be initialised without a constructor
		 * There may possibly be a better solution
		 */
		File dataFile = new File(""); //The name of file to open
		if(returnVal == JFileChooser.APPROVE_OPTION){
			dataFile = choose.getSelectedFile();
		}
		else{
			JOptionPane.showMessageDialog(choose,"Error, File cannot be accepted", "File Error", JOptionPane.ERROR_MESSAGE); //Error message to show if file cannot be accepted
			System.exit(1);//Quiting the program
		}
		return dataFile; //returning the File object
	}

	public void readData(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file)); // File reader
		String line;
		line=br.readLine();

		String[] header=line.split(",");//Splitting by commas/values in the csv file
		headings.addAll(Arrays.asList(header));

		while ((line=br.readLine()) != null) {
			String[] values = line.split(",");//Splitting by commas/values in the csv file
			Student student = new Student(values[0], values[1], values[2], Integer.parseInt(values[19]));
			for (int i = 3; i < 18; i++) {
				if (!values[i].equals("")) student.setMark(headings.get(i), Integer.parseInt(values[i]));
			}
			data.add(student);
		}
	}

	public ArrayList<Student> getData(){ return data; }
	public ArrayList<String> getHeadings(){return this.headings;}
}
