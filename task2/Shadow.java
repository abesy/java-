public class Shadow extends State{
    StateManager stateManager;
    public Shadow(StateManager stateManager){
        this.stateManager = stateManager;
        stateManager.shadow();
    }
}
