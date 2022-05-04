import java.awt.event.*;
import javax.swing.*;

public class PasteButton extends JButton{
    StateManager stateManager;

    public PasteButton(StateManager stateManager){
        super("Paste");
        addActionListener(new PasteListener());
        this.stateManager = stateManager;
    }

    class PasteListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            stateManager.setState(new PasteState(stateManager));
        }
    }
}

