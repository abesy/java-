import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class selectButton extends JButton{
    StateManager stateManager;

    public selectButton(StateManager stateManager){
        super("Select");
        addActionListener(new selectListener());
        this.stateManager = stateManager;
    }

    class selectListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            stateManager.setState(new SelectState(stateManager));
        }
    }
}
