public class RemoveState extends State{
    StateManager stateManager;
    Mediator med;

    public RemoveState(StateManager stateManager){
        this.stateManager = stateManager;
        this.med = stateManager.canvas.mediator;
        if(med.selectedDrawing != null){
            med.removeDrawing();
        }
        med.repaint();
    }
}
