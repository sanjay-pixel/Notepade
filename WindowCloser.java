import java.awt.*;
import java.awt.event.*;
public class WindowCloser extends WindowAdapter
{
    
	public void windowClosing(WindowEvent e1)
	{
		Window w=e1.getWindow();
		w.setVisible(false);
		w.dispose();
	}
}