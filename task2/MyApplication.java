import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyApplication extends JFrame{
    StateManager stateManager;
    MyCanvas canvas;
    public MyApplication(){
        super("My Paint Application");

        canvas = new MyCanvas();
        canvas.setBackground(Color.white);

        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());
        
        stateManager = new StateManager(canvas);

        RectButton rectButton = new RectButton(stateManager);
        jp.add(rectButton);
        OvalButton ovalButton = new OvalButton(stateManager);
        jp.add(ovalButton);
        EqPolygonButton eqPolygonButton = new EqPolygonButton(stateManager);
        jp.add(eqPolygonButton);
        HendecagonalButton hendecagonalButton = new HendecagonalButton(stateManager);
        jp.add(hendecagonalButton);
        //上の図形を引数としてStateButtonによみたい。
        ShadowButton shadowButton = new ShadowButton(stateManager);
        jp.add(shadowButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(jp,BorderLayout.NORTH);
        getContentPane().add(canvas,BorderLayout.CENTER);

        canvas.addMouseListener(new MouseAdapter(){
            //現在のmouseDown処理を呼び出し
            public void mousePressed(MouseEvent e){
                stateManager.mouseDown(e.getX(),e.getY());
                canvas.repaint();
            }
            //MouseReleasedの呼び出し
            public void mouseReleased(MouseEvent e){
                stateManager.mouseUp(e.getX(),e.getY());
            }
        });

        canvas.addMouseMotionListener(new MouseAdapter(){
            //mouseDragの呼び出し
            public void mouseDragged(MouseEvent e){
                stateManager.mouseDrag(e.getX(),e.getY());
                canvas.repaint();
            }
        });
        
        this.addWindowListener(new WindowAdapter(){
            //ウインドウを閉じたら終了
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }

    public Dimension getPerferredSize(){
        return new Dimension(300,400);
    }

    public static void main(String[] args){
        MyApplication app = new MyApplication();
        app.pack();
        app.setVisible(true);
    }
}
