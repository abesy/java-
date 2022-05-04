import java.util.*;
import java.awt.*;
import javax.swing.*;

public class MyCanvas extends JPanel{
    //各図形を格納する
    Mediator mediator;

    public MyCanvas(){
        this.mediator = new Mediator(this);
        setBackground(Color.white);
    }

    public Mediator getMediator(){
        return mediator;
    }


    public void paint(Graphics g){
        super.paint(g);

        Enumeration<MyDrawing> e = mediator.drawingsElements();
        while (e.hasMoreElements()){
            MyDrawing d = e.nextElement();
            d.draw(g);
        }
    }

    public void addDrawing(MyDrawing d){
        mediator.addDrawing(d);
    }

    public void removeDrawing(MyDrawing d){
        mediator.removeDrawing(d);
    }
}
