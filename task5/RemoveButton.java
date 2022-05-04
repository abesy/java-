import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RemoveButton extends JButton{
    StateManager stateManager;

    public RemoveButton(StateManager stateManager){
        super("Remove");
        addActionListener(new removeListener());
        this.stateManager = stateManager;
    }

    class removeListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            stateManager.setState(new RemoveState(stateManager));
        }
    }
}
