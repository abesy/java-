import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ShadowButton extends JButton{
    StateManager stateManager;

    public ShadowButton(StateManager stateManager){
        super("DropShadow");
        addActionListener(new ShadowListener());
        this.stateManager = stateManager;
    }

    class ShadowListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            stateManager.setState(new Shadow(stateManager));
        }
    }
}
