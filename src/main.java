import java.io.IOException;

//main method to run program
public class main{
    public static void main(String args[]) throws IOException{
		Read read = new Read();//creating new read object 
        for (student i : read.data) //iterating though a list of data  {
            i.temp_print(); //printing to show that the program works properly 
        }
        Window window = new Window("Student Marks");//creating a window to show content

}
