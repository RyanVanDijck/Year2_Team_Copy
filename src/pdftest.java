import gnu.jpdf.PDFJob;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class pdftest {
    public static void main(String[] args) throws IOException {
        int a = 5;
        FileOutputStream stream = new FileOutputStream("Hello.pdf");
        PDFJob job = new PDFJob(stream);
        Graphics g = job.getGraphics();
        g.drawString("Hello World",20,20);
        g.dispose();
        g = job.getGraphics();
        g.drawString("Goodbye", 10,10);
        g.dispose();
        job.end();
        stream.close();
    }
}
