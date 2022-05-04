import java.awt.*;
public class EqPolygonState extends State{
    StateManager stateManager;
    int x1,y1,x2,y2;
    MyEqPolygon polygon,shadow;

    public EqPolygonState(StateManager stateManager){
        this.stateManager = stateManager;
    }
    
    public void mouseDown(int x, int y){
        this.x1 = x;
        this.y1 = y;
        polygon = new MyEqPolygon(x,y,8);
        stateManager.addDrawing(polygon);
    }

    public void mouseUp(int x, int y){
        this.x2 = x;
        this.y2 = y;
    }

    public void mouseDrag(int x, int y){
        this.x2 = x;
        this.y2 = y;
        polygon.setSize(x2-x1,y2-y1);
    }
}