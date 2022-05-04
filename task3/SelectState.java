import java.awt.*;

public class SelectState extends State{
    StateManager stateManager;
    Mediator mediator;
    int x1,y1,x2,y2,dx,dy;
    
    public SelectState(StateManager stateManager){
        this.stateManager = stateManager;
        this.mediator = stateManager.canvas.mediator;
    }

    public void mouseDown(int x, int y){
        this.x1 = x;
        this.y1 = y;
        mediator.setSelected(x1, y1);
        dx = mediator.selectedDrawing.getX()-x1;
        dy = mediator.selectedDrawing.getY()-y1;
        
    }


    public void mouseDrag(int x, int y){
        this.x2 = x;
        this.y2 = y;
        if(mediator.selectedDrawing!=null){
            mediator.selectedDrawing.move(x2+dx-mediator.selectedDrawing.getX(),y2+dy-mediator.selectedDrawing.getY());
        }
    }

}
