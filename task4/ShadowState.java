import java.util.Enumeration;
public class ShadowState extends State{
    StateManager stateManager;
    Mediator med;

    public ShadowState(StateManager stateManager){
        this.stateManager = stateManager;
        this.med = stateManager.canvas.mediator;
        if(med.selectedDrawing.isEmpty()!=true){
            Enumeration<MyDrawing> e = med.selectedElements();
            while (e.hasMoreElements()){
                MyDrawing d = e.nextElement();
                med.Shadow(d);
            }
        }
    }
}
