import java.io.IOException;

public class main{
    public static void main(String args[]) throws IOException{
        Read read = new Read();
        for (student i : read.data) {
            i.temp_print();
        }
        Window window = new Window("Student Marks");
    }
}