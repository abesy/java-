import java.awt.*;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.plaf.metal.MetalFileChooserUI;

public class SelectState extends State{
    StateManager stateManager;
    Mediator mediator;
    int x1,y1,x2,y2,dx,dy;
    boolean dragEvent = false;//mouseDragの複数選択に関わる操作を行うかどうかの判定
    MyDrawing selectRect;//複数選択の際に生成する長方形のインスタンスを格納

    public SelectState(StateManager stateManager){
        this.stateManager = stateManager;
        this.mediator = stateManager.canvas.mediator;
    }

    public void mouseDown(int x, int y){
        this.x1 = x;
        this.y1 = y;
        this.x2 = x;
        this.y2 = y;
        if(dragEvent != true){//単一図形選択の際の処理
            mediator.setSelected(x1, y1);
        }
        if(dragEvent == true){//dragEventをfalseにする。→Dragの処理が移動処理に切り替わる
            dragEvent=false;
        }else if(mediator.selectedDrawing.isEmpty()==true && dragEvent==false){//選択範囲の描画処理を追加//ドラッグイベントの処理
            dragEvent = true;//dragEventをtrueにする。→Dragの処理が選択処理に切り替わる
            selectRect = new MyRectangle(x1, y1, 5, 5);
            selectRect.setSelected(true);
            selectRect.setFillColor(new Color(0,0,0,0));//FillColorに透明色を設定
            mediator.addDrawing(selectRect);
        }
    }

    public void mouseUp(int x,int y){
        //dragEvent = false;
        if(selectRect != null){
            mediator.removeDrawing(selectRect);
            selectRect = null;
        }
        if(dragEvent == true){
            Enumeration<MyDrawing> e = mediator.drawingsElements();
            while (e.hasMoreElements()){
                MyDrawing d = e.nextElement();
                if(d.isSelected == true){
                    mediator.setSelectedDrawing(d);
                }
            }
        }
    }


    public void mouseDrag(int x, int y){//マウスダウンしたときとの差分を全図形に対してmove
        dx = x - x2;
        dy = y - y2;
        this.x2 = x;
        this.y2 = y;
        if(dragEvent==true){//選択範囲の描画処理の記述
            selectRect.setSize(x2-x1,y2-y1);//selectRectの描画処理の設定
            Enumeration<MyDrawing> e = mediator.drawingsElements();
            while (e.hasMoreElements()){
                MyDrawing d = e.nextElement();
                //d.setSelected(false);
                if(d.getX()>x1 && d.getY()>y1 && d.getW()+d.getX()<x2 && d.getH()+d.getY()<y2 ||//左上からドラッグ
                    d.getX()>x2 && d.getY()>y2 && d.getW()+d.getX()<x1 && d.getH()+d.getY()<y1||//右下からドラッグ
                    d.getX()>x1 && d.getY()>y2 && d.getW()+d.getX()<x2 && d.getH()+d.getY()<y1||//左下からドラッグ
                    d.getX()>x2 && d.getY()>y1 && d.getW()+d.getX()<x1 && d.getH()+d.getY()<y2){//右上からドラッグ
                    d.setSelected(true);
                }else{
                    d.setSelected(false);
                }
            }
            selectRect.setSelected(true);
        }else if(mediator.selectedDrawing.isEmpty()==false){
            Enumeration<MyDrawing> e = mediator.selectedElements();
            while (e.hasMoreElements()){
                MyDrawing dm = e.nextElement();
                dm.move(dx,dy);
            }
        }
    }
}
