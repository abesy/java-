import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class resizeButton extends JButton{
    StateManager stateManager;

    public resizeButton(StateManager stateManager){
        super("resize");
        addActionListener(new resizeListener());
        this.stateManager = stateManager;
    }

    class resizeListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            stateManager.setState(new resizeState(stateManager));
        }
    }
}
