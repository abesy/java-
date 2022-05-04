import java.awt.*;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JOptionPane;

public class resizeState extends State{
    StateManager stateManager;
    Mediator med;
    int x1,x2,y1,y2;
    int gx, gy, gw, gh;
    int rx,ry,rw,rh;
    MyDrawing selected = null;
    int resizeMode;
    JOptionPane jOptionPane;
    int d;

    public resizeState(StateManager stateManager){
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
            resizeMode = 8;
            rx = selected.getX();
            ry = selected.getY();
            rw = selected.getW();
            rh = selected.getH();
            if((x1 >= rx-d && x1 <= rx+d)&&(y1 >= ry-d && y1 <= ry+d)){
                resizeMode = 0;
            }
            else if((x1 >= rx+rw/2-d && x1 <= rx+rw/2+d)&&(y1 >= ry-d && y1 <= ry+d)){
                resizeMode = 1;
            }
            else if((x1 >= rx+rw-d && x1 <= rx+rw+d)&&(y1 >= ry-d && y1 <= ry+d)){
                resizeMode = 2;
            }
            else if((x1 >= rx+rw-d && x1 <= rx+rw+d)&&(y1 >= ry+rh/2-d && y1 <= ry+rh/2+d)){
                resizeMode = 3;
            }
            else if((x1 >= rx+rw-d && x1 <= rx+rw+d)&&(y1 >= ry+rh-d && y1 <= ry+rh+d)){
                resizeMode = 4;
            }
            else if((x1 >= rx+rw/2-d && x1 <= rx+rw/2+d)&&(y1 >= ry+rh-d && y1 <= ry+rh+d)){
                resizeMode = 5;
            }
            else if((x1 >= rx-d && x1 <= rx+d)&&(y1 >= ry+rh-d && y1 <= ry+rh+d)){
                resizeMode = 6;
            }
            else if((x1 >= rx-d && x1 <= rx+d)&&(y1 >= ry+rh/2-d && y1 <= ry+rh/2+d)){
                resizeMode = 7;
            }
            else{
                resizeMode = 8;
            }
        }
    }

    public void mouseUp(int x, int y){

    }

    public void mouseDrag(int x, int y){
        this.x2 = x;
        this.y2 = y;
        if((resizeMode==0 && x2<rx+rw && y2<ry+rh)||
           (resizeMode==1 && y2<ry+rh)||
           (resizeMode==2 && x2>rx && y2<ry+rh)||
           (resizeMode==3 && x2>rx)||
           (resizeMode==4 && x2>rx && y2>ry)||
           (resizeMode==5 && y2>ry)||
           (resizeMode==6 && x2<rx+rw && y2>ry)||
           (resizeMode==7 && x2<rx+rw)){
            switch(resizeMode){
                case 0: gx = x2; gy = y2; gw = rw + rx - x2; gh = rh + ry - y2; break;
                case 1: gx = rx; gy = y2; gw = rw; gh = rh + ry - y2; break;
                case 2: gx = rx; gy = y2; gw = x2 - rx; gh = rh + ry - y2; break;
                case 3: gx = rx; gy = ry; gw = x2 - rx; gh = rh; break;
                case 4: gx = rx; gy = ry; gw = x2 - rx; gh = y2 - ry; break;
                case 5: gx = rx; gy = ry; gw = rw; gh = y2 - ry; break;
                case 6: gx = x2; gy = ry; gw = rw + rx - x2; gh = y2 - ry; break;
                case 7: gx = x2; gy = ry; gw = rw + rx - x2; gh = ry; break;
            }
            if(gw<0){
                gw = -1 * gw;
            }
            if(gh<0){
                gh = -1 * gh;
            }
            med.resize(gx,gy,gw,gh);
        }
    }
}
