import java.util.Enumeration;
public class PasteState extends State{
    StateManager stateManager;
    Mediator med;
    int x1,x2,y1,y2,dx,dy;
    
    public PasteState(StateManager stateManager){
        this.stateManager = stateManager;
        this.med = stateManager.canvas.mediator;
    }

    public void mouseDown(int x, int y){
        this.x1 = x;
        this.y1 = y;
        this.x2 = x;
        this.y2 = y;
        med.paste(x1,y1);
    }

    public void mouseDrag(int x, int y){
        dx = x - x2;
        dy = y - y2;
        this.x2 = x;
        this.y2 = y;
        Enumeration<MyDrawing> c = med.cloneElements();
        while (c.hasMoreElements()){
            MyDrawing d = c.nextElement();
            d.setSelected(true);
            d.move(dx,dy);
        }
    }
}
