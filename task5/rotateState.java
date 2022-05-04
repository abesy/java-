import java.awt.*;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JOptionPane;

public class rotateState extends State{
    StateManager stateManager;
    Mediator med;
    int x1,x2,y1,y2;
    int gx, gy, gw, gh;
    int rx,ry,rw,rh;
    MyDrawing selected = null;
    int rotateMode;
    JOptionPane jOptionPane;
    int d;

    public rotateState(StateManager stateManager){
        this.stateManager = stateManager;
        this.med = stateManager.canvas.mediator;
        this.d = 3;
    }

    public void mouseDown(int x, int y){
        x1 = x;
        x2 = x;
        y1 = y;
        y2 = y;
        if(med.selectedDrawing.isEmpty() == false && med.selectedDrawing.size() == 1){//最後に選択されたものにリサイズの変更を加える。
            Enumeration<MyDrawing> e = med.selectedElements();
            while (e.hasMoreElements()){
                selected = e.nextElement();
            }
        }else{
            jOptionPane.showMessageDialog(null, "何も選択されていないか、複数のオブジェクトが選択されています", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if(selected != null){
            rotateMode = 8;
            rx = selected.getX();
            ry = selected.getY();
            rw = selected.getW();
            rh = selected.getH();
            if((x1 >= rx-d && x1 <= rx+d)&&(y1 >= ry-d && y1 <= ry+d)){
                rotateMode = 0;
            }
            else if((x1 >= rx+rw-d && x1 <= rx+rw+d)&&(y1 >= ry-d && y1 <= ry+d)){
                rotateMode = 2;
            }
            else if((x1 >= rx+rw-d && x1 <= rx+rw+d)&&(y1 >= ry+rh-d && y1 <= ry+rh+d)){
                rotateMode = 4;
            }
            else if((x1 >= rx-d && x1 <= rx+d)&&(y1 >= ry+rh-d && y1 <= ry+rh+d)){
                rotateMode = 6;
            }
            else{
                rotateMode = 8;
            }
        }
    }

    public void mouseUp(int x, int y){

    }

    public void mouseDrag(int x, int y){
        this.x2 = x;
        this.y2 = y;
        switch(rotateMode){
                case 0: break;
                case 2: break;
                case 4: break;
                case 6: break;
        }
    }
}

