import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class rotateButton extends JButton{
    StateManager stateManager;

    public rotateButton(StateManager stateManager){
        super("resize");
        addActionListener(new rotateListener());
        this.stateManager = stateManager;
    }

    class rotateListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            stateManager.setState(new resizeState(stateManager));
        }
    }
}