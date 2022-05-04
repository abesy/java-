public class CutState extends State{
    StateManager stateManager;
    Mediator med;

    public CutState(StateManager stateManager){
        this.stateManager = stateManager;
        this.med = stateManager.canvas.mediator;
        if(med.selectedDrawing != null){
            med.cut();
        }
    }
}
