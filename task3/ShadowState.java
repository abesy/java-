public class ShadowState extends State{
    StateManager stateManager;
    Mediator med;

    public ShadowState(StateManager stateManager){
        this.stateManager = stateManager;
        this.med = stateManager.canvas.mediator;
        if(med.selectedDrawing != null){
            med.Shadow(med.selectedDrawing);
        }
    }
}
