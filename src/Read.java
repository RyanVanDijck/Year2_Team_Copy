import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.IOException;
import javax.swing.*;
import java.io.File;


//Reading from the CSV file
public class Read {
	private JFileChooser choose = new JFileChooser(".");
    private ArrayList<Student> data = new ArrayList<Student>(); //list of data

	public Read() throws IOException {
        File file = Choose();
		BufferedReader br = new BufferedReader(new FileReader(file)); // File reader
        ArrayList<String> headings = new ArrayList<String>();
        boolean start = true;//to check that we are not on the first line where the headings are
		String line;
        while ((line =br.readLine()) != null){
            String[] values=line.split(",");//Splitting by commas/values in the csv file
            if (start){
                headings.addAll(Arrays.asList(values));
                start = false;
            }
            else{
                Student student = new Student(values[0],values[1],values[2],Integer.parseInt(values[19]));
                for(int i=3;i<18;i++){
                    if(!values[i].equals("")) student.setMark(headings.get(i),Integer.parseInt(values[i]));
                }
                data.add(student);
            }
        }
	}

	//Method to transfer information from the file to instances of the student class
	private File Choose(){
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

	public ArrayList<Student> getData(){ return data; }

}
