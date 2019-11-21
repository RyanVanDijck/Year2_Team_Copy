import java.io.IOException;

//main method to run program
public class main{
    public static void main(String args[]) throws IOException{
		mainWindow window = new mainWindow("Student Marks");//creating a window to show content
		String file = "data.csv"; //Name of file
		Read read = new Read();//creating new read object 
        
        for (student i : read.data){ //iterating though a list of data  
            i.temp_print(); //printing to show that the program works properly 
        }
        
	}
}
