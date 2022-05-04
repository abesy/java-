import java.util.Enumeration;
import java.util.ListIterator;
import java.util.Vector;
import java.awt.*;


public class Mediator{
    Vector<MyDrawing> drawings;
    Vector<MyLayer> layers;
    MyLayer selectedLayer;
    MyCanvas canvas;
    Vector<MyDrawing> selectedDrawing = new Vector<MyDrawing>();;
    Vector<MyDrawing> buffer = new Vector<MyDrawing>();
    Vector<MyDrawing> Clone = new Vector<MyDrawing>();
    MyDrawing clone = null;
    boolean Fill;//otherColorのFill/Line識別用

    public Mediator(MyCanvas canvas){
        this.canvas = canvas;
        layers = new Vector<MyLayer>();
        layers.add(new MyLayer());
        layers.add(new MyLayer());
        layers.add(new MyLayer());
        layers.add(new MyLayer());
        selectedLayer = layers.elementAt(0);
        drawings = selectedLayer.getDrawings();
        Fill = false;
    }

    public void setSelectLayer(int i){//現在のレイアの選択
        selectedLayer = layers.elementAt(i);
        drawings = selectedLayer.getDrawings();
    }

    public void addLayer(){//レイアの追加
        MyLayer newlayer = new MyLayer(); 
        layers.add(newlayer);
    }

    public void removeLayer(){//現在のレイアを消去//使用する際は、この関数の後に新しくレイアを選択する必要がある点に注意
        removeDrawing();
        layers.remove(selectedLayer);
    }

    public Enumeration<MyLayer> layerElements(){
        return layers.elements();
    }

    public Enumeration<MyDrawing> drawingsElements(){//drawingsの要素を取り出す
        return drawings.elements();
    }

    public Enumeration<MyDrawing> selectedElements(){//selectedDrawingの要素を取り出す
        return selectedDrawing.elements();
    }

    public Enumeration<MyDrawing> bufferElements(){//bufferの要素を取り出す
        return buffer.elements();
    }

    public Enumeration<MyDrawing> cloneElements(){//bufferの要素を取り出す
        return Clone.elements();
    }

    public void addDrawing(MyDrawing d){
        drawings.add(d);
    }

    public void removeDrawing(MyDrawing d){
        drawings.remove(d);
        repaint();
    }

    public void resize(int x, int y, int w, int h){
        if(selectedDrawing.isEmpty()==false){
            Enumeration<MyDrawing> e = selectedElements();
            while (e.hasMoreElements()){
                MyDrawing d = e.nextElement();
                d.resize(x,y,w,h);
            }
        }
    }

    public void removeDrawing(){//全ての要素を削除
        if(selectedDrawing.isEmpty()==false){
            Enumeration<MyDrawing> e = selectedElements();
            while (e.hasMoreElements()){
                MyDrawing d = e.nextElement();
                drawings.remove(d);
            }
        }
    }

    public Vector<MyDrawing> getSelectedDrawing(){
        return selectedDrawing;
    }

    public void setSelectedDrawing(MyDrawing d){
        selectedDrawing.add(d);
    }

    public void move(int dx, int dy){
        Enumeration<MyDrawing> e = selectedElements();
        while (e.hasMoreElements()){
            MyDrawing d = e.nextElement();
            d.move(dx,dy);
        }
    }

    public void repaint(){
        canvas.repaint();
    }

    public void setFillColor(Color color){
        if(selectedDrawing.isEmpty()==false){
            Enumeration<MyDrawing> e = selectedElements();
            while (e.hasMoreElements()){
                MyDrawing d = e.nextElement();
                d.setFillColor(color);
            }
        }
    }

    public void setLineColor(Color color){
        if(selectedDrawing.isEmpty()==false){
            Enumeration<MyDrawing> e = selectedElements();
            while (e.hasMoreElements()){
                MyDrawing d = e.nextElement();
                d.setLineColor(color);
            }
        }
    }

    public void setLineWidth(int i){
        if(selectedDrawing.isEmpty()==false){
            Enumeration<MyDrawing> e = selectedElements();
            while (e.hasMoreElements()){
                MyDrawing d = e.nextElement();
                d.setLineWidth(i);
            }
        }
    }

    public void setLineAlpha(int alpha){
        if(selectedDrawing.isEmpty()==false){
            Enumeration<MyDrawing> e = selectedElements();
            while (e.hasMoreElements()){
                MyDrawing d = e.nextElement();
                d.setLineAlpha(alpha);
            }
        }
    }

    public void setFillAlpha(int alpha){
        if(selectedDrawing.isEmpty()==false){
            Enumeration<MyDrawing> e = selectedElements();
            while (e.hasMoreElements()){
                MyDrawing d = e.nextElement();
                d.setFillAlpha(alpha);
            }
        }
    }

    public void clearBuffer(){
        buffer = new Vector<MyDrawing>();
    }

    
    public void copy(){
        clearBuffer();
        if(selectedDrawing.isEmpty()==false){
            Enumeration<MyDrawing> e = selectedElements();
            while (e.hasMoreElements()){
                MyDrawing d = e.nextElement();
                buffer.add(d);
            }
        }
    }

    public void cut(){
        clearBuffer();
        if(selectedDrawing.isEmpty()==false){
            Enumeration<MyDrawing> e = selectedElements();
            while (e.hasMoreElements()){
                MyDrawing d = e.nextElement();
                buffer.add(d);
                removeDrawing(d);
            }
        }
        repaint();
    }
    

    public void paste(int x,int y){
        Enumeration<MyDrawing> c0 = cloneElements();//前回のペーストのselectedを解除
        while (c0.hasMoreElements()){
            MyDrawing d0 = c0.nextElement();
            d0.setSelected(false);
        }
        Clone = new Vector<MyDrawing>();
        if(buffer.isEmpty() == false){
            Enumeration<MyDrawing> e = bufferElements();
            MyDrawing base = e.nextElement();
            clone = (MyDrawing)base.clone();
            clone.setLocation(x,y);
            clone.setRegion();
            base.setSelected(false);
            Clone.add(clone);
            while (e.hasMoreElements()){
                MyDrawing b = e.nextElement();
                clone = (MyDrawing)b.clone();
                clone.setLocation(x+b.getX()-base.getX(),y+b.getY()-base.getY());
                clone.setRegion();
                b.setSelected(false);
                Clone.add(clone);
            }
        }
        Enumeration<MyDrawing> c = cloneElements();
        while (c.hasMoreElements()){
            MyDrawing d = c.nextElement();
            d.setSelected(true);
            addDrawing(d);
        }
        repaint();
    }

    public void Shadow(MyDrawing d){
        if(d.shadow){
            d.shadow = false;
        }else{
            d.shadow = true;
        }
        repaint();
    }

    public void clearSelected(){
        selectedDrawing = new Vector<MyDrawing>();
        selectedDrawing = new Vector<MyDrawing>();
        Enumeration<MyDrawing> e = drawingsElements();
        MyDrawing one = null;
        while (e.hasMoreElements()){
            MyDrawing d = e.nextElement();
            d.setSelected(false);
        }
    }

    public void setSelected(int x, int y){
        selectedDrawing = new Vector<MyDrawing>();
        Enumeration<MyDrawing> e = drawingsElements();
        MyDrawing one = null;
        while (e.hasMoreElements()){
            MyDrawing d = e.nextElement();
            d.setSelected(false);
            if(d.contains(x,y)){
                one = d;
            }
        }
        if(one!=null){
            setSelectedDrawing(one);
        }
        if(selectedDrawing.isEmpty()==false){//isempty
            Enumeration<MyDrawing> s = selectedElements();
            while (s.hasMoreElements()){
                MyDrawing select = s.nextElement();
                select.setSelected(true);
            }
        }
    }

    public void layerSwap(int i, int j){//入力は0ベース
        if(i>j){//i<jに
            int j2 = i;
            i = j;
            j = j2;
        }
        layers.insertElementAt(layers.elementAt(j), i);//jの要素をi番目に挿入
        layers.insertElementAt(layers.elementAt(i+1),j+1);//上ので一つ後ろにずれたことに注意
        layers.remove(i+1);
        layers.remove(j+1);
    }

    public void SetFront(MyDrawing d){
        int ind = drawings.lastIndexOf(d);
        if(ind != drawings.size() - 1){
            drawings.insertElementAt(drawings.elementAt(ind+1),ind);
            drawings.remove(ind+2);
        }
    }

    public void SetSelectedFront(){//選択状態の全ての図形を
        ListIterator<MyDrawing> e = selectedDrawing.listIterator(selectedDrawing.size());
        while (e.hasPrevious()){
            MyDrawing d = e.previous();
            SetFront(d);
        }
    }

    public void SetBack(MyDrawing d){
        int ind = drawings.lastIndexOf(d);
        if(ind != 0){
            drawings.insertElementAt(drawings.elementAt(ind-1),ind+1);
            drawings.remove(ind-1);
        }
    }

    public void SetSelectedBack(){
        Enumeration<MyDrawing> e = selectedElements();
        while (e.hasMoreElements()){
            MyDrawing d = e.nextElement();
            SetBack(d);
        }
    }

    public void SetFrontMost(){
        ListIterator<MyDrawing> e = selectedDrawing.listIterator(selectedDrawing.size());
        int n = 0;
        while (e.hasPrevious()){
            MyDrawing d = e.previous();
            while(drawings.lastIndexOf(d) != drawings.size()-1-n){
                SetFront(d);
            }
            n += 1;
        }
    }

    public void SetBackMost(){
        Enumeration<MyDrawing> e = selectedElements();
        int n = 0;
        while (e.hasMoreElements()){
            MyDrawing d = e.nextElement();
            while(drawings.lastIndexOf(d) != n){
                SetBack(d);
            }
            n += 1;
        }
    }
}