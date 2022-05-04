import java.awt.*;

public class OvalState extends State{
    StateManager stateManager;
    int x1,y1,x2,y2;
    MyOval oval ,shadow;

    public OvalState(StateManager stateManager){
        this.stateManager = stateManager;
    }

    public void mouseDown(int x, int y){
        this.x1 = x;
        this.y1 = y;
        if(stateManager.shadow){
            shadow = new MyOval(x1+5,y1+5,0,0);
            shadow.setFillColor(Color.black);
            stateManager.addDrawing(shadow);
        }
        oval = new MyOval(x1,y1,0,0);
        stateManager.addDrawing(oval);
    }

    public void mouseUp(int x, int y){
        this.x2 = x;
        this.y2 = y;
    }

    public void mouseDrag(int x, int y){
        this.x2 = x;
        this.y2 = y;
        oval.setSize(Math.abs(x2-x1),Math.abs(y2-y1)); 
        if(stateManager.shadow){
            shadow.setSize(x2-x1,y2-y1);
        }
    }
}