import java.util.Enumeration;
import java.util.Vector;
import java.awt.*;


public class Mediator{
    Vector<MyDrawing> drawings;
    MyCanvas canvas;
    Vector<MyDrawing> selectedDrawing = new Vector<MyDrawing>();;
    Vector<MyDrawing> buffer = new Vector<MyDrawing>();
    Vector<MyDrawing> Clone = new Vector<MyDrawing>();
    MyDrawing clone = null;
    boolean Fill;//otherColorのFill/Line識別用

    public Mediator(MyCanvas canvas){
        this.canvas = canvas;
        drawings = new Vector<MyDrawing>();
        Fill = false;
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
        //setSelectedDrawing(d);
    }

    public void removeDrawing(MyDrawing d){
        drawings.remove(d);
        repaint();
    }

    public void removeDrawing(){
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

    public void setSelected(int x, int y){
        selectedDrawing = new Vector<MyDrawing>();
        Enumeration<MyDrawing> e = drawingsElements();
        MyDrawing one = null;
        while (e.hasMoreElements()){
            MyDrawing d = e.nextElement();
            d.setSelected(false);
            if(d.contains(x,y)){
                one = d;
                //setSelectedDrawing(d);
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
}