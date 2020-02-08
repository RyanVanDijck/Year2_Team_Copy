import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import gnu.jpdf.PDFJob;
import java.io.FileOutputStream;
import java.io.IOException;

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
        ActionListener ex = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    export(graph);
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
            }
        };
        JButton button = new JButton("Hello");
        button.addActionListener(ex);
        this.add(button,BorderLayout.NORTH);
        this.add(graph);
        this.pack();
        this.setSize(500,500);
        //this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);

    }
    public void export(graph graph) throws IOException {
        JFileChooser chooser = new JFileChooser(".");
        chooser.setDialogTitle("Save File");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF files", "pdf");
        chooser.setSelectedFile(new File(module + ".pdf"));
        chooser.setFileFilter(filter);
        int select = chooser.showSaveDialog(this);
        if(select == JFileChooser.APPROVE_OPTION){
            FileOutputStream stream = new FileOutputStream(chooser.getSelectedFile());
            PDFJob job = new PDFJob(stream);
            Graphics g = job.getGraphics();
            graph.bar(g,0.34);
            g.dispose();
            job.end();
            stream.close();
        }
        else{
            JOptionPane.showMessageDialog(this,"File could not be opened"
            , "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class graph extends JPanel{

    public void paintComponent(Graphics g) {
        bar(g);
    }

    public void scatter(Graphics g){
        int start = 20;
        int gap = 15;

        for (int i = 0; i < GraphFrame.tempList.size(); i++) {
            g.fillOval(start, GraphFrame.tempList.get(i)*6, 5,5);
            if (i < GraphFrame.tempList.size() - 1){
                g.drawLine(start,GraphFrame.tempList.get(i)*6,start + gap,GraphFrame.tempList.get(i+1)*6);
            }

            start = start + gap ;
        }
    }

    public void bar(Graphics g){
        int start = 10;
        int gap = 15;
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(start,this.getHeight() - 10,this.getWidth() - 10,this.getHeight() - 10); //horizontal
        g2.drawLine(start,this.getHeight() -10,start,0); // vertical
        start += 10;
        g2.setStroke(new BasicStroke());
        for (int i = 0; i < GraphFrame.tempList.size(); i++) {
            //g.fillOval(start, GraphFrame.tempList.get(i) * 6, 5, 5);
            g2.drawLine(start - 5,this.getHeight() -10,start - 5,GraphFrame.tempList.get(i) * 6);
            g2.drawLine(start + 5,this.getHeight() -10,start + 5,GraphFrame.tempList.get(i) * 6);
            g2.drawLine(start -5,GraphFrame.tempList.get(i) * 6 , start + 5,GraphFrame.tempList.get(i)*6);
            start = start + gap;
        }

    }

    public void bar(Graphics g, Double scale){
        Graphics2D temp = (Graphics2D) g;
        temp.scale(scale,scale);
        Graphics g2 = (Graphics)temp;
        bar(g2);
    }
}