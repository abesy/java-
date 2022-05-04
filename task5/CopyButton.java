import java.awt.event.*;
import javax.swing.*;

public class CopyButton extends JButton{
    StateManager stateManager;

    public CopyButton(StateManager stateManager){
        super("Copy");
        addActionListener(new CopyListener());
        this.stateManager = stateManager;
    }

    class CopyListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            stateManager.setState(new CopyState(stateManager));
        }
    }
}
