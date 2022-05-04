public class PasteState extends State{
    StateManager stateManager;
    Mediator med;
    int x1,x2,y1,y2;
    
    public PasteState(StateManager stateManager){
        this.stateManager = stateManager;
        this.med = stateManager.canvas.mediator;
    }

    public void mouseDown(int x, int y){
        x1 = x;
        y1 = y;
        med.paste(x1,y1);
    }

    public void mouseDrag(int x, int y){
        this.x2 = x;
        this.y2 = y;
        med.clone.move(x2-med.clone.getX(),y2-med.clone.getY());   
    }
}
