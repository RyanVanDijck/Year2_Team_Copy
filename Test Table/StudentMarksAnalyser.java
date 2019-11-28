import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.Map;
import java.util.HashMap;
//main method to run program
public class StudentMarksAnalyser {

    public static void main(String[] args) throws IOException{
      try {
  			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
  		} catch (ClassNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (InstantiationException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (IllegalAccessException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (UnsupportedLookAndFeelException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}

      StudentMarksAnalyserUI window = new StudentMarksAnalyserUI("Student Marks");//creating a window to show content
		// String file = "data.csv"; //Name of file
        window.setVisible(true);
		Read read = new Read();//creating new read object
    String[][] array = new String[read.getData().size() ][19];
    String[] headings = new String[19];
    int tempCount = 3;
    for (int i = 0;i < read.getHeadings().size()-1;i++){
      headings[i] = read.getHeadings().get(i);
    }
    headings[18] = "Average Mark";
        for (int i = 0; i < read.getData().size() ; i++){ //iterating though a list of data
            array[i][0] = read.getData().get(i).getRegNo();
            array[i][1] = read.getData().get(i).getExamNo();
            array[i][2] = read.getData().get(i).getStage();
            tempCount = 3;
            for (Map.Entry<String,Integer> index: read.getData().get(i).getMap().entrySet()){
            if (!(index.getValue() == null)){
              array[i][tempCount] = Integer.toString(index.getValue());
            }
            else{
              array[i][tempCount] = "N/A";
            }
              tempCount++;
            }
            array[i][18] = Integer.toString(read.getData().get(i).getAvgMark());
        }

      for (String[] i: array){
        for (String i2 : i){
        System.out.print(i2 + " ");
      }
      System.out.println();
      }
        window.addTable(array,headings);


	}
}
