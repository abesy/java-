import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StateManager{
    State state;
    MyCanvas canvas;
    boolean shadow;
    public StateManager(MyCanvas canvas){
        this.canvas = canvas;
        shadow = false;
    }

    public void setState(State state){
        this.state = state;
    }

    public void addDrawing(MyDrawing d){
        canvas.addDrawing(d);
    }

    public void removeDrawing(MyDrawing d){
        canvas.removeDrawing(d);
    }
    
    public void mouseDown(int x, int y){
        state.mouseDown(x,y);
    }

    public void mouseUp(int x, int y){
        state.mouseUp(x,y);
    }

    public void mouseDrag(int x, int y){
        state.mouseDrag(x,y);
    }

    public void shadow(){
        if(shadow){
            shadow = false;}
        else{
            shadow = true;
        }
    };

}
