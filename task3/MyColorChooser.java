import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MyColorChooser{
    JColorChooser colorchooser;
    Mediator med;


    public MyColorChooser(){
        Colorchooser c = new Colorchooser();
        c.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        c.setBounds(10, 10, 450, 400);
        c.setTitle("other color");
        c.setVisible(true);
    }

    private class Colorchooser extends JFrame implements ChangeListener{
        JColorChooser colorchooser;
        Colorchooser(){
            colorchooser = new JColorChooser();
            colorchooser.getSelectionModel().addChangeListener(this);
            getContentPane().add(colorchooser,BorderLayout.CENTER);
            getContentPane().add(colorchooser,BorderLayout.PAGE_START);
        }
        public void stateChanged(ChangeEvent e){
            Color color = colorchooser.getColor();
            if(med.Fill){
                med.setFillColor(color);
            }
            else{
                med.setLineColor(color);
            }
        }
    }
}
