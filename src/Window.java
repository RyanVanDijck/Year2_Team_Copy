import javax.swing.*;
import java.awt.*;
public class Window extends JFrame {
    TextArea output = new TextArea();
    JPanel visual = new JPanel();
    JPanel data = new JPanel();
    public Window(String name){
        super(name);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(output,BorderLayout.SOUTH);
        add(visual,BorderLayout.EAST);
        add(data,BorderLayout.WEST);
        pack();
        setSize(800,800);
        setVisible(true);
    }
}
