import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EqPolygonButton extends JButton{
    StateManager stateManager;
    int n;
    JOptionPane jOptionPane;

    public EqPolygonButton(StateManager stateManager){
        super("Polygon");

        addActionListener(new EqPolygonListener());

        this.stateManager = stateManager;
    }

    class EqPolygonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            n = Integer.parseInt(jOptionPane.showInputDialog(null,"数値を入力してください","各数の設定",JOptionPane.PLAIN_MESSAGE));
            stateManager.setState(new EqPolygonState(stateManager,n));
        }
    }
}
