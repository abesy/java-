import java.awt.*;

public class RectState extends State {
    StateManager stateManager;
    int x1,y1,x2,y2;
    MyRectangle rect;

    public RectState (StateManager stateManager){
        this.stateManager = stateManager;
    }

    public void mouseDown(int x, int y){
        this.x1 = x;
        this.y1 = y;
        rect = new MyRectangle(x1,y1,0,0);
        stateManager.addDrawing(rect);
    }

    public void mouseUp(int x, int y){
        this.x2 = x;
        this.y2 = y;
    }

    public void mouseDrag(int x, int y){
        this.x2 = x;
        this.y2 = y;
        rect.setSize(x2-x1,y2-y1);
    }
}