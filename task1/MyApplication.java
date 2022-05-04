import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyApplication extends JFrame{
    public MyApplication(){
        super("My Painter");

        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        this.getContentPane().add(jp);

        MyCanvas canvas = new MyCanvas();

        int n = 1;
        for(int i=0; i<n; i++){
            canvas.addDrawing(new MyEqPolygon(150, 150, 60, Color.black, Color.white, 15));
        }
        jp.add(BorderLayout.CENTER, canvas);

        //Windowイベント
        this.addWindowListener(
            new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                    System.exit(1);
                }
            }
        );
    }

    
    public MyApplication(int w, int h){
        this();
        this.setSize(w,h);
    }


    public static void main(String[] args){
        MyApplication app = new MyApplication(300,300);
        //app.setSize(400,300);
        app.setVisible(true);
    }
    
}
