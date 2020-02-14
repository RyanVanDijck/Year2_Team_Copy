import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ChooseHandler implements ActionListener {
    StudentMarksAnalyser main;
    Read read;
    String[][] array;
    String[] headings;
    public ChooseHandler(StudentMarksAnalyser main) throws IOException {
        this.main=main;
    }
    public void actionPerformed(ActionEvent e) {
        try {
            read = new Read();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        array = new String[read.getData().size() ][19];
        headings = new String[19];
        for (int i = 0;i < read.getHeadings().size()-1;i++){
            headings[i] = read.getHeadings().get(i);
        }
        headings[18] = "Average Mark";
        for (int i = 0; i < read.getData().size() ; i++){ //iterating though a list of data
            array[i][0] = read.getData().get(i).getRegNo();
            array[i][1] = read.getData().get(i).getExamNo();
            array[i][2] = read.getData().get(i).getStage();
            for (int j=3; j<18; j++){
                if (!(read.getData().get(i).getMark(headings[j]) == null)){
                    array[i][j] = Integer.toString(read.getData().get(i).getMark(headings[j]));
                }
                else{
                    array[i][j] = "N/A";
                }
            }
            array[i][18] = Integer.toString(read.getData().get(i).getAvgMark());
        }

        for (String[] i: array){
            for (String i2 : i){
                System.out.print(i2 + " ");
            }
            System.out.println();
        }
        main.UI.addTable(array,headings);
        main.setRead(read);

    }
}
