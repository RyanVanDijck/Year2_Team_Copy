import java.io.IOException;

//main method to run program
public class main{
    public static void main(String args[]) throws IOException{
		mainWindow window = new mainWindow("Student Marks");//creating a window to show content
		String file = "data.csv"; //Name of file
        window.setVisible(true);
		Read read = new Read();//creating new read object
        
        for (student i : read.data){ //iterating though a list of data
            window.output.append(i.ExNo +" "+ i.ce101 + " "+ i.ave_mark+"\n");
            //i.temp_print(); //printing to show that the program works properly
        }

        
	}
}
