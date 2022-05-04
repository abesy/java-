import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HendecagonalButton extends JButton{
    StateManager stateManager;

    public HendecagonalButton(StateManager stateManager){
        super("Hendecagonal");

        addActionListener(new HendecagonalListener());

        this.stateManager = stateManager;
    }

    class HendecagonalListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            stateManager.setState(new HendecagonalState(stateManager));
        }        
    }
}