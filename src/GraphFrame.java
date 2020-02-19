import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import gnu.jpdf.PDFJob;
import java.io.FileOutputStream;
import java.io.IOException;
//Class created to show graph of
//Students results for a chosen module
public class GraphFrame extends JFrame {
    //List of students
    ArrayList<Student> list;
    //The module code
    String module;
    //List of results used by graph class
    static ArrayList<Integer> tempList;
    BufferedImage logo = (ImageIO.read(getClass().getResource("/SCANALYZERLOGO.png")));

    //The statistics for the class

    static Double mean;
    static Double max;
    static Double min;
    static Double SD;
    static Double range;

    static String mod;
    boolean showframe;
    graph graph = new graph();


    public GraphFrame(ArrayList<Student> list, String module, boolean showFrame) throws IOException {
        super();
        //Changing the logo of the program
        this.setIconImage(this.logo);
        this.list = list;
        this.module = module;
        mod = module;
        this.setTitle(module);
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for(Student i: list){
            if (i.getMark(module) != null){
                temp.add(i.getMark(module));
            }
        }
        //Sorting grades to make trend of graph more clear
        temp.sort(Collections.reverseOrder());
        tempList = temp;
        graph graph = new graph();
       //Action listener to save graph to pdf
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
        //setting the statistics
        mean = StudentMarksAnalyserUI.statistics.getMean(this.module);
        max = StudentMarksAnalyserUI.statistics.getMax(this.module);
        min = StudentMarksAnalyserUI.statistics.getMin(this.module);
        SD = StudentMarksAnalyserUI.statistics.getSD(this.module);
        range =StudentMarksAnalyserUI.statistics.getRange(this.module);

        JButton button = new JButton("Export");
        button.addActionListener(ex);
        this.add(button, BorderLayout.NORTH);
        this.add(graph);
        this.pack();
        //this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //displaying frame
        if (showFrame) {
            this.setVisible(true);
        }
    }

    public void getBar(Graphics g, double scale) throws IOException {
        graph.bar(g,scale);
    }

    //method to export graph to pdf
    public void export(graph graph) throws IOException {

        //allowing user to select file save location
        JFileChooser chooser = new JFileChooser(".");
        chooser.setDialogTitle("Save File");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF files", "pdf");
        chooser.setSelectedFile(new File(module + ".pdf"));
        chooser.setFileFilter(filter);
        int select = chooser.showSaveDialog(this);
        if(select == JFileChooser.APPROVE_OPTION){
            //Creating a file out put stream
            FileOutputStream stream = new FileOutputStream(chooser.getSelectedFile());
            //Using the pdf job class from the gnujpdf api
            PDFJob job = new PDFJob(stream);
            //Accessing the graphics of the pdf document
            Graphics g = job.getGraphics();
            //Drawing bar chart onto graph
            //Reducing scale to allow it to fit onto the page
            graph.bar(g,0.34);
            //Closing connection to pdf document
            g.dispose();
            job.end();
            stream.close();
        }
        else{
            //Showing error message if file location not allowed
            JOptionPane.showMessageDialog(this,"File could not be opened"
            , "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
//class to draw graph onto frame
class graph extends JPanel{
    Font font = new Font("arial", Font.BOLD, 20);
    //The scale that the graph should be drawn at
    Double scale = 1.0;

    public void paintComponent(Graphics g) {
        bar(g);
    }
    //drawing a scatter graph of results
    public void scatter(Graphics g){
        int start = 20; //The position of first point
        int gap = 15; //The distance to increase by each point
        //iterating though list of marks
        for (int i = 0; i < GraphFrame.tempList.size(); i++) {
           //plotting point
            g.fillOval(start, GraphFrame.tempList.get(i)*6, 5,5);
            //drawing line from point to point
            if (i < GraphFrame.tempList.size() - 1){
                g.drawLine(start,GraphFrame.tempList.get(i)*6,start + gap,GraphFrame.tempList.get(i+1)*6);
            }
            //incrementing point
            start = start + gap ;
        }
    }
    //drawing a bar chart of results
    public void bar(Graphics g){
        int start = 10; //The position of first point
        int gap = 15;//The distance to increase by each point
        //Converting to graphics 2d to allow me to make lines thicker
        Graphics2D g2 = (Graphics2D) g;
       //making lines thicker
        g2.setStroke(new BasicStroke(3));
        //Drawing axis
        g2.drawLine(start,this.getHeight() - 10,this.getWidth() - 10,this.getHeight() - 10); //horizontal
        g2.drawLine(start,this.getHeight() -10,start,0); // vertical
        //incrementing point
        start += 10;
        //returning the thickness of line to default
        g2.setStroke(new BasicStroke());
        for (int i = 0; i < GraphFrame.tempList.size(); i++) {
            //drawing a bar for each value
            g2.drawLine(start - 5, this.getHeight() -10,start - 5,GraphFrame.tempList.get(i) * 6);
            g2.drawLine(start + 5,this.getHeight() -10,start + 5,GraphFrame.tempList.get(i) * 6);
            g2.drawLine(start -5,GraphFrame.tempList.get(i) * 6 , start + 5,GraphFrame.tempList.get(i)*6);
            start = start + gap;
        }
        int yval = 50;

        g2.setFont(font);
        g2.drawString("Mean: " + GraphFrame.mean.toString(), 20, yval);
        yval+=30 * scale;
        g2.drawString("Range: " + GraphFrame.range.toString(),20,yval);
        yval+=30 * scale;
        g2.drawString("Minimum Value: " + GraphFrame.min.toString(),20,yval);
        yval += 30 * scale;
        g2.drawString("Maximum Value: " + GraphFrame.max.toString(),20,yval);
        yval +=30 * scale;
        g2.drawString("Standard Deviation: " + GraphFrame.SD.toString(),20,yval);
        g2.drawString(GraphFrame.mod, (int)(this.getHeight() * scale), (int)((20 / scale)));

    }
    //a function to draw a graph to a certain scale
    public void bar(Graphics g, Double scale){
        //initializing graphics
        this.scale = scale;
        Graphics2D temp = (Graphics2D) g;
        font= new Font("Arial", Font.BOLD, (int) (20 * scale));
        //setting scale
        temp.scale(scale,scale);
        //converting to correct data type for function
        //calling g argument function
        //runs as normal
        bar((Graphics)temp);
    }

}
