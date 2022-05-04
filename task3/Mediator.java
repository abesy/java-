import java.util.Enumeration;
import java.util.Vector;
import java.awt.*;


public class Mediator{
    Vector<MyDrawing> drawings;
    MyCanvas canvas;
    MyDrawing selectedDrawing = null;
    MyDrawing buffer = null;
    MyDrawing clone = null;
    boolean Fill;//otherColorのFill/Line識別用

    public Mediator(MyCanvas canvas){
        this.canvas = canvas;
        drawings = new Vector<MyDrawing>();
        Fill = false;
    }

    public Enumeration<MyDrawing> drawingsElements(){
        return drawings.elements();
    }

    public void addDrawing(MyDrawing d){
        drawings.add(d);
        setSelectedDrawing(d);
    }

    public void removeDrawing(MyDrawing d){
        drawings.remove(d);
    }

    public MyDrawing getSelectedDrawing(){
        return selectedDrawing;
    }

    public void setSelectedDrawing(MyDrawing d){
        this.selectedDrawing = d;
    }

    public void move(int dx, int dy){
        if(selectedDrawing != null){
            selectedDrawing.move(dx,dy);
        }
    }

    public void repaint(){
        canvas.repaint();
    }

    public void setFillColor(Color color){
        if(selectedDrawing != null){
            selectedDrawing.setFillColor(color);
        }
    }

    public void setLineColor(Color color){
        if(selectedDrawing != null){
            selectedDrawing.setLineColor(color);
        }
    }

    public void setLineWidth(int i){
        if(selectedDrawing != null){
            selectedDrawing.setLineWidth(i);
        }
    }

    public void clearBuffer(){
        buffer = null;
    }

    
    public void copy(){
        clearBuffer();
        if(selectedDrawing != null){
            buffer = selectedDrawing.clone();
        }
    }

    public void cut(){
        clearBuffer();
        if(selectedDrawing != null){
            buffer = selectedDrawing.clone();
            removeDrawing(selectedDrawing);
        }
        repaint();
    }

    public void paste(int x,int y){
        if(buffer != null){
            selectedDrawing.isSelected = false;
            selectedDrawing = null;
            clone = (MyDrawing)buffer.clone();
            clone.setLocation(x,y);
            clone.setRegion();
            addDrawing(clone);
            repaint();
        }
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
        if(selectedDrawing != null){
            selectedDrawing = null;
        }
        Enumeration<MyDrawing> e = drawingsElements();
        while (e.hasMoreElements()){
            MyDrawing d = e.nextElement();
            d.setSelected(false);
            if(d.contains(x,y)){
                setSelectedDrawing(d);
            }
        }
        if(selectedDrawing != null){
            selectedDrawing.setSelected(true);
        }
    }
}