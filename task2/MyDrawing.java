import java.awt.*;

public class MyDrawing {
    private int x, y, w, h;
    private Color lineColor, fillColor;
    private int lineWidth;

    public MyDrawing(){
        x = y = 0;
        w = h = 40;
        lineColor = Color.black;
        fillColor = Color.white;
        lineWidth = 1;
    }

    public void draw(Graphics g){
    
    }

    public void move(int dx, int dy){
        x += dx;
        y += dy;
    }
    
    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setSize(int w, int h){
        this.w = w;
        this.h = h;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getW(){
        return this.w;
    }

    public int getH(){
        return this.h;
    }

    public int getLineWidth(){
        return this.lineWidth;
    }

    public Color getFillColor(){
        return this.fillColor;
    }

    public Color getLineColor(){
        return this.lineColor;
    }

    public void setLineColor(Color lineColor){
        this.lineColor = lineColor;
    }

    public void setFillColor(Color fillColor){
        this.fillColor = fillColor;
    }

    public void setLineWidth(int lineWidth){
        this.lineWidth = lineWidth;
    }
}
