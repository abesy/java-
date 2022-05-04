import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EqPolygonButton extends JButton{
    StateManager stateManager;

    public EqPolygonButton(StateManager stateManager){
        super("Polygon");

        addActionListener(new EqPolygonListener());

        this.stateManager = stateManager;
    }

    class EqPolygonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            stateManager.setState(new EqPolygonState(stateManager));
        }
    }
}
