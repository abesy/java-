import java.awt.*;

public class HendecagonalState extends State{
    StateManager stateManager;
    int x1,y1,x2,y2;
    MyHendecagonal hendecagonal,shadow;

    public HendecagonalState(StateManager stateManager){
        this.stateManager = stateManager;
    }
    
    public void mouseDown(int x, int y){
        this.x1 = x;
        this.y1 = y;
        hendecagonal = new MyHendecagonal(x,y,0);
        if(stateManager.shadow){
            shadow = new MyHendecagonal(x1+5,y1+5,0);
            shadow.setFillColor(Color.black);
            stateManager.addDrawing(shadow);
        }
        stateManager.addDrawing(hendecagonal);
    }

    public void mouseUp(int x, int y){
        this.x2 = x;
        this.y2 = y;
    }

    public void mouseDrag(int x, int y){
        this.x2 = x;
        this.y2 = y;
        hendecagonal.setSize(x2-x1,y2-y1);
        if(stateManager.shadow){
            shadow.setSize(x2-x1,y2-y1);
        }
    }
}