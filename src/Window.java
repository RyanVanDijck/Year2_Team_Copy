/*class to create a frame to display data */

import javax.swing.*;
import java.awt.*;
public class Window extends JFrame { //a class which extends the JFrame to add custom elements 
    TextArea output = new TextArea(); //A text area to display program output 
    JPanel visual = new JPanel(); // A panel to show visual aspects 
    JPanel data = new JPanel(); //A spare panel for now 
    public Window(String name){
        super(name); //calling the normal JFrame constructor 
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //The program will close when the window closes 
        //adding the objects 
		add(output,BorderLayout.SOUTH);
        add(visual,BorderLayout.EAST);
        add(data,BorderLayout.WEST);
		//Packing the frame 
        pack();
        //setting the frame size 
		setSize(800,800);
        //making the frame visible 
		setVisible(true);
    }
}
