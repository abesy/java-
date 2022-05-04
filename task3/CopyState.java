public class CopyState extends State{
    StateManager stateManager;
    Mediator med;

    public CopyState(StateManager stateManager){
        this.stateManager = stateManager;
        this.med = stateManager.canvas.mediator;
        if(med.selectedDrawing != null){
            med.copy();
        }
    }
}
