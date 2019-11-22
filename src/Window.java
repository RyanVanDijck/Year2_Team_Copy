import javax.swing.*;
import java.awt.*;
/*class to create a frame to display data */
public class Window extends JFrame { //a class which extends the JFrame to add custom elements 
    public Window(String name,int x,int y){
        super(name); //calling the normal JFrame constructor 
        //Packing the frame 
        pack();
        //setting the frame size 
		setSize(x,y);
    }
	


}

class mainWindow extends Window{
	TextArea output = new TextArea(); //A text area to display program output 
    JPanel visual = new JPanel(); // A panel to show visual aspects 
    JPanel data = new JPanel(); //A spare panel for now 
	public mainWindow(String name){
		super(name,800,800);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //The program will close when the window closes 
		//adding the objects 
		this.add(output,BorderLayout.SOUTH);
		this.add(visual,BorderLayout.EAST);
		this.add(data,BorderLayout.WEST);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
    }
}
