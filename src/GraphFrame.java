import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class GraphFrame extends JFrame {
    ArrayList<Student> list;
    String module;
    static ArrayList<Integer> tempList;

    public GraphFrame(ArrayList<Student> list, String module){
        super();
        this.list = list;
        this.module = module;
        this.setTitle(module);
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for(Student i: list){
            if (i.getMark(module) != null){
                temp.add(i.getMark(module));
            }
        }
        temp.sort(Collections.reverseOrder());
        tempList = temp;
        graph graph = new graph();

        this.add(graph);
        this.pack();
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}

class graph extends JPanel{

    public void paintComponent(Graphics g) {
        int count = 0;

        for (int i = 0; i < GraphFrame.tempList.size(); i++) {
            g.fillOval(count, GraphFrame.tempList.get(i)*6, 5,5);
            if (i < GraphFrame.tempList.size() - 1){
                g.drawLine(count,GraphFrame.tempList.get(i)*6,count + 10,GraphFrame.tempList.get(i+1)*6);
            }

            count = count + 10 ;
        }
    }
}